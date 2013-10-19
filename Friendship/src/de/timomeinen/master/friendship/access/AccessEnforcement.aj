/*
 * Friendship-Framework -
 * Extended and fine-grained access control in Java
 * Copyright (C) 2008 Timo Meinen
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 *
 * Das Friendship-Framework entstand im Rahmen meiner Masterarbeit.
 * Autor:   Timo Meinen (mail@TimoMeinen.de)
 * Quellen: http://TimoMeinen.de
 *
 */

package de.timomeinen.master.friendship.access;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.FieldSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.ConstructorSignature;

import de.timomeinen.master.friendship.annotations.Friendship;
import de.timomeinen.master.friendship.exception.FriendshipAccessException;

/**
 * Die Klasse {@link AccessEnforcement} überprüft Zugriffe auf Methoden und
 * Felder, die mit {@link Friendship} annotiert wurden. Falls ein Zugriff 
 * unterbunden wird, wirft die Klasse einen {@link IllegalAccessError}, der
 * vom Aufrufer natürlich abgefangen werden kann.
 * 
 * Der Enforcer setzt einen Teil des Reference-Monitors (siehe Masterarbeit)
 * um.
 *  
 * @author Timo Meinen
 *
 */
public aspect AccessEnforcement {

	/** Logger */
	private static Log logger = LogFactory.getLog(AccessEnforcement.class);
	
	/*
	 * ************* Helper *****************
	 */

	/**
	 * Diese Methode fuehrt die eigentliche Ueberpruefung aus.
	 * 
	 * Funktionsweise: Mit Hilfe des {@link JoinPoint} wird der Aufrufer
	 * analysiert und mit den Freund-Klassen und Freund-Paketen aus der
	 * {@link Friendship} Annotation verglichen. Ist der Aufrufer ein Freund,
	 * wird der Zugriff gewaehrt, ansonsten unterbunden.
	 * 
	 * @param esp EnclosingStaticPoint wird benötigt für die aufrufende Methode
	 * @param sp StaticPoint wird benötigt für statische Aufrufe
	 * @param caller Der Aufrufer
	 * @param target Der Aufgerufene
	 * @param friends Die Friendship Annotation
	 * @param constructor true, wenn es sich um einen Konstruktor Aufruf handlt
	 * oder allgemein, wenn die Genehmigung nicht erteilt werden soll, wenn der
	 * Aufrufer gleich Aufgerufener ist.
	 */
	private void checkPermission(
			JoinPoint.StaticPart esp, 
			JoinPoint.StaticPart sp, 
			Object caller, 
			Object target, 
			Friendship friends, 
			boolean constructor) 
	{
		/** Der Aufrufer */
		Class<?> callerClass;
		/*
		 * Ist ein Aufrufer eine statische Methode, kann natürlich nicht auf das
		 * Objekt zugegriffen werden. Für diesen Fall wird dann mit Hilfe der
		 * {@link SourceLocation} auf die Klasse zugegriffen. Diese wird
		 * benötigt, um an den Namen der Klasse und des Paketes zu gelangen.
		 */
		if (caller == null)
			callerClass = sp.getSourceLocation().getWithinType();
		else
			callerClass = caller.getClass();
		
		String callerMethod = getCallerMethod(esp, friends);
		
		/** Der Aufgerufene */
		Class<?> targetClass;
		if (target == null)
			targetClass = sp.getSignature().getDeclaringType();	
		else
			targetClass = target.getClass();	
			
		logger.trace(	"\n Caller: " + callerClass +
						"\n Target: " + targetClass +
						"\n CallerMethod: " + callerMethod);
		
		/*
		 * *********** Klassen überprüfen *************
		 */
		boolean accessGranted = checkFields(friends, callerClass, targetClass, constructor);
		
		/*
		 * *********** Methoden überprüfen *************
		 */
		
		if ( !accessGranted && (callerMethod.length() != 0) )
			accessGranted = checkMethods(callerMethod, friends) ? true : accessGranted;
		
		/*
		 * *********** Pakete überprüfen *************
		 */		
		
		if (!accessGranted)
			accessGranted = checkPackages(friends, callerClass, targetClass) ? true : accessGranted;

		/*
		 * *********** AccessRule überprüfen *************
		 */
		
		if (!accessGranted)
			accessGranted = checkAccessRule(callerClass, targetClass, caller, target, friends) ? true : accessGranted;
		
		
		/*
		 * *********** Auswertung *************
		 */

		if (accessGranted) {
			// Der Zugriff wurde erlaubt
			logger.debug("Access granted to: " + callerMethod);
		}
		else {
			// Der Zugriff wurde verweigert
			
			// constructor = create; access = modify;
			String tempResult = constructor ? "create" : "modify";
			String result = "\n Access Denied for Class '" + callerClass + "'\n trying to " + tempResult + "\t '" + targetClass +"'";
			logger.error(result);			
			
			// Falls in der Friendship-Annotation eine ErrorMessage als Fehler
			// oder als Tipp hinterlegt wurde, wird diese ausgegeben
			String friendErrorMessage = friends.errorMessage();
			if (friendErrorMessage.length() == 0)
				throw new FriendshipAccessException(result);			
			else {
				logger.error("Friendship-Error:\n " +friends.errorMessage()+ "\n");
				throw new FriendshipAccessException(result, friends);
			}
		}
	}


	/**
	 * Überprüft, ob die aufrufende Methode eine Freund-Methode ist. Dies kann
	 * über das Attribut friendMethods der Friendship Annotation bestimmt werden.
	 * <p>
	 * Es können auch die Wildcards '*' und '?' verwendet werden.
	 * <p>
	 * Die Deklaration erfolgt über den voll qualifizierten Namen der Klasse und
	 * dem Methodennamen, gefolgt von zwei Klammern ().
	 * <p>
	 * Beispiel:
	 * de.timomeinen.master.ClassA.testMethod()
	 * de.timomeinen.master.ClassA.test*()
	 * de.timomeinen.*
	 * 
	 * @param callerMethod Die aufrufende Methode als String.
	 * @param friends Die Friendship Annotation
	 */
	private boolean checkMethods(String callerMethod, Friendship friends) {
		for (String method : friends.friendMethods()) {
//			logger.info("checkMethods: \n Caller: " + callerMethod + "\n Friend: " + method);
			if (method.equals(callerMethod)) {
				return true;
			} else {
				// Es können auch Wildcard-Methoden angegeben werden
				// z.B. friendMethodss = "de.timomeinen.test*()"
				String regex = method.replace("*", ".*");
				regex = regex.replace("?", ".?");
				
				if (callerMethod.matches(regex))
					return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Wird eine eigene AccessRule angegeben führt diese Methode die hasAccess(..)-
	 * Methode aus.
	 */
	private boolean checkAccessRule(
			Class callerClass,
			Class targetClass,
			Object caller, 
			Object target, 
			Friendship friends) 
	{
		Class<? extends AccessRule> ar = friends.accessRule();
		if (ar != AccessRule.class) {
			// Eine eigene AccessRule wurde angegeben
			AccessRule accessRule = getAccessRuleInstance(ar);
			
			boolean accessGranted = false;
			if ( (caller == null) || (target == null) )
				accessGranted = accessRule.hasStaticAccess(callerClass, targetClass, friends);
			else		
				accessGranted = accessRule.hasAccess(caller, target, friends);
			
			return accessGranted;
		} else
			return false;		
	}


	/**
	 * Überprüft, ob sich der Aufrufer in einem befreundeten Paket befindet. 
	 */
	private boolean checkPackages(Friendship friends, Class<?> callerClass,	Class<?> targetClass) {
		// Berechtigung wird erteilt, falls sich die Klassen in einem Paket
		// befinden UND packageAccess = true aktiviert wurde
		if (friends.packageAccess()) {
			Package callerPackage = callerClass.getPackage();
			Package targetPackage = targetClass.getPackage();
			
			if ( callerPackage.equals(targetPackage) )
				return true;
		}
		
		// Berechtigung wird erteilt, wenn ein befreundetes Package
		// verwendet wird
		String[] friendPackages = friends.friendPackages();
		if (( friendPackages.length != 0) && 
			( callerClass.getPackage() != null) ) 
		{
			// Es wurde ein Friend-Package angegeben
			
			/** Das Package des Aufrufers */
			String callerPackage = callerClass.getPackage().getName();
			
			for (String friendPackage : friendPackages)
				if (callerPackage.equalsIgnoreCase(friendPackage)) {
					logger.trace("Access granted to Package:  " + callerPackage);
					return true;					
				} 
				else {
					// Es können auch Wildcard-Pakete angegeben werden
					// z.B. friendPackages = "de.timomeinen.*"
					String regex = friendPackage.replace("*", ".*");
					regex = regex.replace("?", ".?");
					
					if (callerPackage.matches(regex)) {
						logger.trace("Access granted to Package: " + callerPackage +"\n due to wildcard expression: " +friendPackage);
						return true;
					}
				}
		}
		return false;
	}

	/**
	 * Überprüft, ob der Aufrufer einer der befreundeten Klassen ist. Falls
	 * inheritanceAccess aktiviert ist, gewährt die Methode auch dann Zugriff,
	 * wenn der Aufrufer eine befreundete Klasse oder Schnittstelle erweitert
	 * bzw. implementiert.  
	 */
	private boolean checkFields(
			Friendship friends, 
			Class callerClass,
			Class targetClass,
			boolean constructor) {		
		
		String caller = callerClass.getName();
		String target = targetClass.getName();
		
		// Berechtigung wird erteilt, wenn es die eigene Klasse ist
		// oder wenn es eine der Freundklassen ist
		if (caller.equals(target) && !constructor) {
			// Der Aufrufer ruft sich selbst auf => OK
			logger.trace("Access granted 'Fields' in caller.equals(target). \n Target: " + target + " \n Caller: " + caller);
			return true;
		}
		else {
			// Der Aufrufer ist jemand fremdes
			for (Class<?> friend : friends.friendClasses()) {
				if (caller.equals(friend.getName())) {
					// Der Aufrufer ist eine Freund-Klasse
					logger.trace("Access granted 'Fields' in caller.equals(friend)");
					return true;
				}
				
				if (friends.inheritanceAccess()) {
					// Vererbte Klassen haben ebenfalls Zugriff.
					try {
						/*
						 * Es wird ein Cast durchgeführt auf die Freund-Klasse.
						 * Falls dieser erfolgreich ist, handelt es sich, um
						 * eine vererbte Klasse und der Zugriff wird gewährt.
						 */
						callerClass.asSubclass(friend);
						return true;
					} catch (ClassCastException e) {}
				}
			}
		}
		return false;
	}


	/**
	 * Liefert eine Instanz der eigenen {@link AccessRule}.
	 *   
	 * @param accessRule Die eigene AccessRule-Class
	 * @return Die Instanz der eigneen AccessRule
	 */
	private AccessRule getAccessRuleInstance(Class<? extends AccessRule> accessRule) {
		/*
		 * Alle Exceptions werden zunächst abgefangen. Vielleicht muss heir noch
		 * eine genauere Fehlerbehandlung ausgeführt werden.
		 */
		try {
			/*
			 * Zunächst muss überprüft werden, ob es sich um eine Klasse oder um eine
			 * Member-Klasse handelt. Member-Klassen lassen sich nicht einfach instanziieren,
			 * weshalb diese Methode rekursiv auf die enclosedClassed aufgerufen wird.
			 */
			if (accessRule.isMemberClass()) {
				Class enclosingClass = accessRule.getEnclosingClass();
				AccessRule enclosingAccessRule = getAccessRuleInstance(enclosingClass);

				// Hat der Constructor nur einen Parameter wird die innere Klasse als Parameter übergeben 
				Constructor constructor = accessRule.getDeclaredConstructor(new Class[] {enclosingClass});
				
				/*
				 * Soll eine innere Klasse instanziiert werden, so muss die umgebene
				 * Klasse als Parameter übergeben werden. Das ist wie bei 
				 * <code>Inner() inner = outer.new Inner();</code>
				 */
				return (AccessRule)constructor.newInstance(enclosingAccessRule);
			}
			else
				/*
				 *  'Normale' Klassen können direkt über newInstance instanziiert werden.
				 *  
				 *  TODO Cache anlegen
				 */
				return accessRule.newInstance();
			
		} catch (SecurityException e) {			
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Liefert die Namen der Freund-Klassen und der Freund-Pakete einer Methode
	 * zurueck
	 */
	Friendship getMethodFriends(JoinPoint jp) {
		MethodSignature ms = (MethodSignature) jp.getSignature();
		Method m = ms.getMethod();

		// Methode ist in einer Superclass
		if (m == null) {
			Class<?> clazz = ms.getDeclaringType().getSuperclass(); // Travelling
																	// temp
																	// class
			String methodName = ms.getName(); // Methodname
			Class<?>[] parameterTypes = ms.getParameterTypes(); // Method-Parameter

			// Go up the hierarchy until Object is reached
			while (clazz != Object.class) {
				try {
					m = clazz.getDeclaredMethod(methodName, parameterTypes);
					logger.trace("Found method in " + clazz);
					break;
				} catch (NoSuchMethodException e) {
					logger.trace("Found no method in " + clazz);
					// Go up the hierarchy
					clazz = clazz.getSuperclass();
				}
			}

			// Es konnte keine Methode gefunden werden
			if (m == null) {
				String result = "Es wurde keine Methode '"
					+ methodName + "' in der Klassenhierarchie von '"
					+ ms.getDeclaringType() + "' gefunden.";
				
				logger.fatal(result);
				throw new NoSuchMethodError(result);
			}
			
		}
		
		Friendship friendship = m.getAnnotation(Friendship.class);
		return friendship;
	}

	private Friendship getClassFriends(JoinPoint.StaticPart staticPart) {
		Class target = staticPart.getSignature().getDeclaringType();
		return (Friendship)target.getAnnotation(Friendship.class);
	}
	
	
	Friendship getConstructorFriends(JoinPoint jp) {
		ConstructorSignature cs = (ConstructorSignature) jp.getSignature();
		Constructor c = cs.getConstructor();
		Friendship friends = (Friendship)c.getAnnotation(Friendship.class);
		return friends;
	}
	
	/**
	 * Liefert die Namen der Freund-Klassen und der Freund-Pakete eines Feldes
	 * zurueck.
	 */
	Friendship getFieldFriends(JoinPoint jp) {
		FieldSignature fs = (FieldSignature) jp.getSignature();
		Field f = fs.getField();
		Friendship friends = f.getAnnotation(Friendship.class);
		return friends;
	}
	
	/**
	 * Liefert den Namen der aufrufenden Methode
	 * 
	 * @param esp EnclosingStaticPart
	 * @param friends Friend-annotation
	 */
	private String getCallerMethod(JoinPoint.StaticPart esp, Friendship friends) {
		if (friends.friendMethods().length != 0)
			return esp.getSignature().getDeclaringTypeName() + "." + esp.getSignature().getName() + "()";
		else
			return "";
	}
	
	/*
	 * ************* Pointcuts *****************
	 */
	
	/*
	 * Alle Methoden, die mit Friendship annotiert sind
	 * Objekte werden mit O bezeichnet
	 * Statische Elemente werden mit S bezeichnet
	 */

	// Methoden: Caller O, Target O 
	pointcut protectMethods(Object caller, Object target): 
		this(caller) &&
		target(target) &&
		call(@Friendship * *(..));

	// Methoden: Caller S, Target O
	pointcut protectMethodFromStatic(Object target): 
		target(target) &&
		!this(Object) &&
		call(@Friendship * *(..));
	
	// Methoden: Caller O, Target S
	pointcut protectMethodToStatic(Object caller): 
		this(caller) &&
		!target(Object) &&
		call(@Friendship * *(..));

	// Methoden: Caller S, Target S
	pointcut protectMethodFromStaticToStatic(): 
		!this(Object) && 
		!target(Object) &&
		call(@Friendship * *(..));

	
	/**
	 * Alle Felder, die mit Friendship annotiert sind
	 */
	// Felder: Caller O, Target O
	pointcut protectFields(Object caller, Object target):
		this(caller) &&
		target(target) &&
		( set(@Friendship * *.*) || get(@Friendship * *.*) );
	
	// Felder: Caller S, Target O
	pointcut protectFieldsFromStatic(Object target):
		!this(Object) &&
		target(target) &&
		( set(@Friendship * *.*) || get(@Friendship * *.*) );
	
	// Felder: Caller O, Target S
	pointcut protectFieldsToStatic(Object caller):
		this(caller) &&
		!target(Object) &&
		( set(@Friendship * *.*) || get(@Friendship * *.*) );
	
	// Felder: Caller S, Target S
	pointcut protectFieldsFromStaticToStatic():
		!this(Object) &&
		!target(Object) &&
		( set(@Friendship * *.*) || get(@Friendship * *.*) );
	
	/**
	 * Alle Konstruktoren, die mit Friendship annotiert sind
	 */
	// Konstruktor: Caller O
	pointcut protectConstructors(Object caller): 
		this(caller) &&
		call(@Friendship *.new(..));
	
	// Konstruktor: Caller S
	pointcut protectConstructorsStatic(): 
		!this(Object) &&
		call(@Friendship *.new(..));
	
	/**
	 * Alle Konstruktoren, deren Klassen mit Friendship annotiert sind.
	 * Das bedeutet, dass alle Konstruktoren geschützt werden.
	 */
	// Konstruktor: Caller O
	pointcut protectClasses(Object caller): 
		this(caller) &&
		call((@Friendship *).new(..));
	
	// Konstruktor: Caller S
	pointcut protectClassesStatic(): 
		!this(Object) &&
		call((@Friendship *).new(..));
	
	
	// ********** ADVICES ************
	
	// *** METHODEN ***
	before(Object caller, Object target): protectMethods(caller, target) {
		Friendship friends	= getMethodFriends(thisJoinPoint);				
		checkPermission(thisEnclosingJoinPointStaticPart, thisJoinPointStaticPart, caller, target, friends, false);
	}
	
	before(Object target): protectMethodFromStatic(target) {
		Friendship friends	= getMethodFriends(thisJoinPoint);				
		checkPermission(thisEnclosingJoinPointStaticPart, thisJoinPointStaticPart, null, target, friends, false);
	}
	
	before(Object caller): protectMethodToStatic(caller) {
		Friendship friends	= getMethodFriends(thisJoinPoint);				
		checkPermission(thisEnclosingJoinPointStaticPart, thisJoinPointStaticPart, caller, null, friends, false);
	}

	before(): protectMethodFromStaticToStatic() {
		Friendship friends	= getMethodFriends(thisJoinPoint);				
		checkPermission(thisEnclosingJoinPointStaticPart, thisJoinPointStaticPart, null, null, friends, false);
	}
	
	// *** Felder ***
	before(Object caller, Object target): protectFields(caller, target) {
		Friendship friends = getFieldFriends(thisJoinPoint);
		checkPermission(thisEnclosingJoinPointStaticPart, thisJoinPointStaticPart, caller, target, friends, false);
	}
	
	before(Object target): protectFieldsFromStatic(target) {
		Friendship friends = getFieldFriends(thisJoinPoint);
		checkPermission(thisEnclosingJoinPointStaticPart, thisJoinPointStaticPart, null, target, friends, false);
	}
	
	before(Object caller): protectFieldsToStatic(caller) {
		Friendship friends = getFieldFriends(thisJoinPoint);
		checkPermission(thisEnclosingJoinPointStaticPart, thisJoinPointStaticPart, caller, null, friends, false);
	}
	
	before(): protectFieldsFromStaticToStatic() {
		Friendship friends = getFieldFriends(thisJoinPoint);
		checkPermission(thisEnclosingJoinPointStaticPart, thisJoinPointStaticPart, null, null, friends, false);
	}
	
	
	// *** Konstruktor ***
	before(Object caller):protectConstructors(caller) {
		Friendship friends = getConstructorFriends(thisJoinPoint);
		checkPermission(thisEnclosingJoinPointStaticPart, thisJoinPointStaticPart, caller, null, friends, true);
	}
	
	before():protectConstructorsStatic() {
		Friendship friends = getConstructorFriends(thisJoinPoint);
		checkPermission(thisEnclosingJoinPointStaticPart, thisJoinPointStaticPart, null, null, friends, true);
	}
	
	
	// *** Klassen ***
	before(Object caller):protectClasses(caller) {
		Friendship friends = getClassFriends(thisJoinPointStaticPart);
		checkPermission(thisEnclosingJoinPointStaticPart, thisJoinPointStaticPart, caller, null, friends, true);
	}
	
	before():protectClassesStatic() {
		Friendship friends = getClassFriends(thisJoinPointStaticPart);
		checkPermission(thisEnclosingJoinPointStaticPart, thisJoinPointStaticPart, null, null, friends, true);
	}
	
	/*
	 * TODO Cache anlegen
	 * TODO Friendship-Klassenweit anlegen können
	 */
}
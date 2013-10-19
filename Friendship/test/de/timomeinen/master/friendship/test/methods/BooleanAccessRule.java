package de.timomeinen.master.friendship.test.methods;

import de.timomeinen.master.friendship.access.AccessRule;
import de.timomeinen.master.friendship.annotations.Friendship;

public class BooleanAccessRule extends AccessRule {

	@Override
	public boolean hasAccess(Object caller, Object target, Friendship friend) {
		AllMethodTest t = (AllMethodTest) caller;
		if (t.access)
			return true;
		else
			return false;
	}

	@Override
	public boolean hasStaticAccess(Class caller, Class target, Friendship friend) {
		// TODO Auto-generated method stub
		return false;
	}

}

package de.timomeinen.master.permissions;

import java.security.BasicPermission;
import java.security.Permission;

public class FriendshipPermission extends BasicPermission {

	private String target;
	
	public FriendshipPermission(String name, String target) {
		super(name);
		this.target = target;
	}
	
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getActions() {
		// TODO Auto-generated method stub
		return null;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean implies(Permission permission) {
		if (!( permission instanceof FriendshipPermission ))
			return false;
		
		FriendshipPermission perm = (FriendshipPermission)permission;
				
		if (!( perm.target.equals(this.target) )) {
			System.out.println("Aufrufer " +perm.target+ " ist kein Freund!");
			return false;
		}

		return true;
	}
}

package de.timomeinen.master.permissions;

import java.security.AccessController;

public class FriendshipClass {

	public void protectedMethod(String caller) {
		System.out.println("A protected method called from: " + caller);

		FriendshipPermission perm = new FriendshipPermission(this.getClass().getName(), caller);
		AccessController.checkPermission(perm);

		System.out.println("Access granted.");
	}
}

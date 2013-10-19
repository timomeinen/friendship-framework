Friendship-Framework
====================

Das Friendship-Framework (FF) ist eine erweiterte Zugriffssteuerung für Java-Anwendungen auf Objektebene mit einer feinen Granularität. Der Zugriff wird dabei nicht mehr anhand des Subjekts kontrolliert, sondern auf Grundlage des aufrufenden Objektes.

Das Framework kann in allen Java-Anwendungen verwendet werden, die mit Hilfe des eingesetzten aspektorientierten Compilers übersetzt werden können. Zur Konfiguration (und damit das Erstellen der Regelwerke der Zugriffskontrolle) werden Annotationen eingesetzt.

Die Funktionen und Möglichkeiten werden in meiner Masterarbeit im Kapitel 4 vorgestellt: [Masterarbeit von Timo Meinen](http://timomeinen.de/development/Masterarbeit_Timo_Meinen_final.pdf)

Dies sind die Programmtexte des Friendship-Frameworks.

Die Programmteile stehen unter der Lizenz GPLv2, die in der Datei 'gpl-2.0.txt'
abgedruckt ist.

Verzeichnisse
=============
Die einzelnen Programmmodule sind in Verzeichnisse aufgeteilt. Alle lassen sich
als fertiges Projekt in Eclipse importieren. Der Pfad zu den Bibliotheken muss
aber noch angepasst werden. Zus‰tzlich werden die folgenden Plugins fÜr Eclipse
benötigt:

- AspectJ Development Tools (AJDT) (http://eclipse.org/ajdt)
- SpoonJDT (http://spoon.gforge.inria.fr)

Friendship
----------
Dies ist das Framework mit seinen aspektorientierten Teilen.

FriendshipSpoon
---------------
Das Spoonlet, welches die Sichtbarkeiten der annotierten Felder veröffentlicht

Master Case Study
-----------------
Fallbeispiele der Masterarbeit mit integrierten Friendship-Framework Zugriffsschutz

Master Examples
---------------
Beispiele ohne Framework, die zu dieser Masterarbeit motivierten. Bruch der Datenkapselung etc.

PolicyImplementation
--------------------
Standard Java Policy Implementierung des Freundschaftsprinzips.

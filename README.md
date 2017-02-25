# After-Hour
Das Projekt After-Hour besteht aus drei Anwendungen: 
- Server-Anwendung
- Kunden-Anwendung (Android App)
- Betreiber-Anwendung (Android App)

## Server
Der Server ist gegenüber der Mobile Application eine REST-Schnittstelle. Die Android App darf nicht direkt mit der Datenbank sprechen, deshalb übernimmt der Server die Datenbankanbindung.

### Play Framework
Für die REST - Schnittstelle wird das [Play Framework](https://playframework.com/) verwendet. In diesem Projekt werden wir das Play Framework mit Hilfe von Java verwenden (nicht Scala!).

#### Installation Play Framework
Zur Installation des Play Frameworks muss zuerst die Anwendung SBT installiert werden. SBT ist eine Konsole, mit der das Aufsetzen und Ausführen des Play Servers automatisiert werden kann. 
Für die Installation von SBT, folge die Anleitungen unter http://www.scala-sbt.org/

#### Ausführen des Servers
Öffnen eine Konsole im Root-Ordner des Servers und führe folgenden Befehl aus: `sbt run`
Der Server sollte jetzt gestartet werden und unter `localhost:9000` erreichbar sein.

**Anmerkung:** Bei jedem Request prüft der Server, ob es Änderungen im Code gegeben hat. Wenn es eine Änderung gab oder der Server noch nie kompiliert wurde, wird vor der Antwort des Request die komplette Serveranwendung noch einmal kompiliert. Dies kann durchaus einige Sekunden dauern.

### Hibernate
Als ORM wird JPA in Verbindung mit [Hibernate](http://hibernate.org/orm/) verwendet. So, wie es jetzt geplant ist, werden wir eine relationale Datenbank (MySQL) verwenden. 

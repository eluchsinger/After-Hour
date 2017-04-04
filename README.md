[![Build Status](https://travis-ci.com/eluchsinger/After-Hour.svg?token=qGdUXq6yMCLLxngwYqhT&branch=master)](https://travis-ci.com/eluchsinger/After-Hour)
 [![JitPack Status](https://jitpack.io/v/eluchsinger/After-Hour-Shared.svg)](https://jitpack.io/#eluchsinger/After-Hour-Shared)
 [![Code Climate](https://codeclimate.com/repos/58e3bdb881e1cb040f00046d/badges/81c8ec9bfaf7055fd482/gpa.svg)](https://codeclimate.com/repos/58e3bdb881e1cb040f00046d/feed)
 [![Test Coverage](https://codeclimate.com/repos/58e3bdb881e1cb040f00046d/badges/81c8ec9bfaf7055fd482/coverage.svg)](https://codeclimate.com/repos/58e3bdb881e1cb040f00046d/coverage)
 [![Issue Count](https://codeclimate.com/repos/58e3bdb881e1cb040f00046d/badges/81c8ec9bfaf7055fd482/issue_count.svg)](https://codeclimate.com/repos/58e3bdb881e1cb040f00046d/feed)


# After-Hour
Das Projekt After-Hour besteht aus drei Anwendungen: 
- Server-Anwendung
- Benutzer-Anwendung (Android App)

## Server
Der Server ist gegenüber der Mobile Application eine REST-Schnittstelle. Die Android App darf nicht direkt mit der Datenbank sprechen, deshalb übernimmt der Server die Datenbankanbindung.

### Play Framework
Für die REST - Schnittstelle wird das [Play Framework](https://playframework.com/) verwendet. In diesem Projekt werden wir das Play Framework mit Hilfe von Java verwenden (nicht Scala!).

#### Installation Play Framework
Zur Installation des Play Frameworks muss zuerst die Anwendung SBT installiert werden. SBT ist eine Konsole, mit der das Aufsetzen und Ausführen des Play Servers automatisiert werden kann. 
Für die Installation von SBT, folge die Anleitungen unter http://www.scala-sbt.org/
>   Tipp: Prüfe, ob SBT schon installiert ist mit dem Befehl ```sbt sbtVersion```

#### Ausführen des Servers
Öffnen eine Konsole im Root-Ordner des Servers und führe folgenden Befehl aus: `sbt run`
Der Server sollte jetzt gestartet werden und unter `localhost:9000` erreichbar sein.

**Anmerkung:** Bei jedem Request prüft der Server, ob es Änderungen im Code gegeben hat. Wenn es eine Änderung gab oder der Server noch nie kompiliert wurde, wird vor der Antwort des Request die komplette Serveranwendung noch einmal kompiliert. Dies kann durchaus einige Sekunden dauern.

### Pusher
Um proaktiv vom Server zur Android Anwendung Events zu versenden, verwenden wir den [Pusher Service](https://pusher.com/). Ein Android Tutorial ist [hier](https://pusher.com/docs/android_quick_start) verfügbar.

### Hibernate
Als ORM wird JPA in Verbindung mit [Hibernate](http://hibernate.org/orm/) verwendet. So, wie es jetzt geplant ist, werden wir eine relationale Datenbank (MySQL) verwenden. 


This is your new Play application
=================================

This file will be packaged with your application when using `activator dist`.

There are several demonstration files available in this template.

Controllers
===========

- HomeController.java:

  Shows how to handle simple HTTP requests.

- AsyncController.java:

  Shows how to do asynchronous programming when handling a request.

- CountController.java:

  Shows how to inject a component into a controller and use the component when
  handling requests.

Components
==========

- Module.java:

  Shows how to use Guice to bind all the components needed by your application.

- Counter.java:

  An example of a component that contains state, in this case a simple counter.

- ApplicationTimer.java:

  An example of a component that starts when the application starts and stops
  when the application stops.

Filters
=======

- Filters.java:

  Creates the list of HTTP filters used by your application.

- ExampleFilter.java

  A simple filter that adds a header to every response.

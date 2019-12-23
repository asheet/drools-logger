# drools-rules-logger

Maven Project for creating a drools logger for importing into Business Central or kie-workbench

Run `mvn clean install` or `mvn clean package`

Take the jar and import into Business Central or Kie-Workbench through the Admin Page

Register the event listener in the kmodule.xml of the kjar project that needs to execute those event listeners.

Add dependency of Listener in *Your Project* > Settings > Dependencies > Add dependency

In Business Central/Kie-Workbench, under *Your Project* > Settings > Kie bases > Add KIE base > give name “default” > Click "Kie sessions" > Add Kie session > give name “default” > click “>” # Listeners > Add Listener > specify Type (com.drools_logger_listener.RuleLoggerEventListener) and Kind (AgendaEventListener) > Done




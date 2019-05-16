package tran.example.basicwebapp;

public class Car {

    private Engine engine;

    private String warningMessage;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public void accelerate() {
//        engine.increaseRpm(); // video has this but it's an empty body definition... so I'm choosing to leave it out..?
        if(engine.getRpm() > 5000) {
            warningMessage = "Slow Down!";
        } else {
            warningMessage = "";
        }
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }
}

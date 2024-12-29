package org.example;

public class Task5 {
    public static void main(String[] args) {
        TrafficLightContext context = new TrafficLightContext();
        context.transitionToGreen();
        context.transitionToYellow();
        context.transitionToRed();
    }
}
interface TrafficLightState{
    void transitionToRed(TrafficLightContext context);
    void transitionToGreen(TrafficLightContext context);
    void transitionToYellow(TrafficLightContext context);
}
class RedLightState implements TrafficLightState{
    @Override
    public void transitionToRed(TrafficLightContext context){
        System.out.println("Already in red state.");
    }
    @Override
    public void transitionToGreen(TrafficLightContext context){
        System.out.println("Switching to green.");
        context.setState(new GreenLightState());
    }

    @Override
    public void transitionToYellow(TrafficLightContext context){
        System.out.println("Switching to yellow.");
        context.setState(new YellowLightState());
    }
}
class YellowLightState implements TrafficLightState{
    @Override
    public void transitionToRed(TrafficLightContext context){
        System.out.println("Switching to red.");
        context.setState(new RedLightState());
    }

    @Override
    public void transitionToGreen(TrafficLightContext context){
        System.out.println("Switching to green.");
        context.setState(new GreenLightState());
    }

    @Override
    public void transitionToYellow(TrafficLightContext context){
        System.out.println("Already in yellow state.");
    }
}
class GreenLightState implements TrafficLightState{
    @Override
    public void transitionToRed(TrafficLightContext context){
        System.out.println("Switching to red.");
        context.setState(new RedLightState());
    }
    @Override
    public void transitionToGreen(TrafficLightContext context){
        System.out.println("Already in green state.");
    }
    @Override
    public void transitionToYellow(TrafficLightContext context){
        System.out.println("Switching to yellow.");
        context.setState(new YellowLightState());
    }
}
class TrafficLightContext{
    private TrafficLightState state;
    public TrafficLightContext(){
        state = new RedLightState(); // Initial state
    }
    public void setState(TrafficLightState state) {
        this.state = state;
    }
    public void transitionToRed() {
        state.transitionToRed(this);
    }
    public void transitionToGreen() {
        state.transitionToGreen(this);
    }
    public void transitionToYellow() {
        state.transitionToYellow(this);
    }
}

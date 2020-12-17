public abstract class RomLoader {
//RomLoader has bus, so it can stop the current running game of program and load other.
public Bus bus;
public RomLoader(){
    bus = new Bus();

}
abstract public void turnOn();
    
}

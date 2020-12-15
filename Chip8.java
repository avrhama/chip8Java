

public class Chip8 {

    public static void main(String[] args) {
        //*NOTE: this project not follwing 100% opp principles.
        //for example encapsulation principle was ignored, for avoiding function(Getter) overhead and its makes the code much clear(for me at least).
        Bus bus = new Bus();
        bus.configBus();
        bus.loadRom("C:\\Users\\Epsilon\\Documents\\roms\\chip8\\Space Invaders [David Winter].ch8");
        bus.turnOn();

    }
}

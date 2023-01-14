package polymorphism;

public class LgTV implements TV {
	public void powerOn() {
		System.out.println("LgTv---전원 켠다.");
	}
	public void powerOff() {
		System.out.println("LgTv---전원 끈다.");
	}
	public void volumeUp() {
		System.out.println("LgTv---소리 올린다.");
	}
	public void volumeDown() {
		System.out.println("LgTv---소리 내린다.");
	}
}

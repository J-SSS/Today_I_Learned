package polymorphism;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv")
public class LgTV implements TV {
	@Autowired
	@Qualifier("apple")
	private Speaker speaker;
	
	public void powerOn() {
		System.out.println("LgTv---전원 켠다.");
	}
	public void powerOff() {
		System.out.println("LgTv---전원 끈다.");
	}
	public void volumeUp() {
		speaker.volumUp();
//		System.out.println("LgTv---소리 올린다.");
	}
	public void volumeDown() {
		speaker.volumDown();
//		System.out.println("LgTv---소리 내린다.");
	}
}

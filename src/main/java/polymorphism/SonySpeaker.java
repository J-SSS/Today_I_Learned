package polymorphism;

public class SonySpeaker implements Speaker {
	public SonySpeaker() {
		System.out.println("===> SonySpeaker 按眉 积己");
	}
	@Override
	public void volumUp() {
		System.out.println("SonySpeaker --- 家府 棵赴促.");
	}
	@Override
	public void volumDown() {
		System.out.println("SonySpeaker --- 家府 郴赴促.");
	}
}

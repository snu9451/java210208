package network.step1;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

import javax.swing.JFrame;
/* [소켓]
 * <무전기에 장착된 하드웨어>에 비유할 수 있겠다.
 * 네트워크에서 정보가 들어가고 나가는 지점에 대한 유일한 식별자
 * 32비트의 숫자로 구성되며,
 * <짝수번 소켓>은 정보를 받아들이는 용도로,
 * <홀수번 소켓>은 정보를 보내는 용도로 사용됨.
 * * 네트워크 상의 소통은< First In First Out>의 속성을 갖는다. (자료구조에서 '큐(Queue)': 운영체제의 요청을 순차적으로 처리)
 * * cf) 스택(stack) - 백팩가방과 같다. First In Last Out의 속성을 갖는다.
 * 동시에 두 가지를 할 수는 없다. 즉, '읽으면서 쓰기' 또는 '쓰면서 읽기'는 불가능하다.
 * 그래서 소켓을 <홀수번>과 <짝수번>으로 나눠서 운영한다고 이해할 수 있겠다.
 * 일단은 물리적으로 네트워크망이 구성되어 있어야 한다. 네트워크망을 통한 읽기와 쓰기가 가능하도록 인프라가 형성되어 있어야 한다.
 * 
 * 
 * [TCP포트번호]	--물리적인 장치를 꽂는 장소는 아니다! 용도에 따라 쓰는 숫자(값)이다.
 * 서버에서 돌아가는 특정 프로그램을 나타내는 16비트 숫자이다.
 * 웹서버(ex-네이버..)	- 80
 * 텔넷서버				- 23
 * FTP서버			- 20
 * SMTP서버			- 25
 * 같은 포트에서 '여러 프로그램'이 돌아갈 수 있을까?
 * NO, BindException이 발생한다.
 * netstat -ano | findstr 3000 검색
 * 삭제하고 싶으면, taskkill /pid 42356 /f		--여기서 42356은 프로세스에 대한 id임.
 */
public class TimeServer extends Thread{
	//RMBR) (전역변수)선언부 자리에는 실행문이 올 수 없다!!
//	int i;		따라서 이건 불법
//	i = 10;
	Socket socket = null;
	public TimeServer() {}
	public TimeServer(Socket socket) {
		this.socket = socket;
	}
	public static void initDisplay() {
		new JFrame().setVisible(true);
	}
	/************************************************************
	 * 스레드 기동 시 호출되는 <콜백 메소드>이다.
	 ************************************************************/
	@Override
	public void run() {
		boolean isFlag = false;
		try {
			//socket에 대한 객체 주입은 인스턴스화를 통해서 생성자가 호출되었을 때 이루어진다.
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);	//소켓이 어디서 사용되고 있나? --여기서 소켓이 사용되고 있다.
			while(!isFlag) {
				if(out!=null) {
					out.println(getTimer());
				}
				//1초동안 기다려!....그리고 진행....1초 기다려!....진행...
				try {
					sleep(1000);	//밀리세컨드 단위로 설정함. 따라서 1000이 1초임	//이곳에서 지연이 발생한다(> 스레드)
					//경합>스레드 / 순서>스레드 / 순서를 정한다>스레드 ...
				} catch (InterruptedException ie) {
					System.out.println("누구세요 -_-");
				}
			}
		} catch (Exception e) {
			
		}
		System.out.println("Run Call ...");
	}
	
	public String getTimer() {
		Calendar 	cal 	= Calendar.getInstance();
		int			hour	= cal.get(Calendar.HOUR_OF_DAY);
		int			min		= cal.get(Calendar.MINUTE);
		int			sec		= cal.get(Calendar.SECOND);
//		String		timenow = String.format("현재시각> %02d시 %02d분 %02d초", hour, min, sec);
		//삼항연산자	//참일때 ":"의 앞의 것, 거짓일 때 ":"의 뒤의 것
		return (hour< 10 ? "0" + hour : "" + hour) + ":"
			 + (min< 10 ? "0" + min : "" + min) + ":"
			 + (sec< 10 ? "0" + sec : "" + sec);
//		return timenow;
	}
	
	/*본만큼 구현한다!
	* 지금처럼 main메소드 안에 구성하는 것은 좋지 않다.
	* 왜냐하면 메인 메소드도 스레드이기 때문이다. (main메소드를 디폴트 스레드라고도 한다. - main메소드는 가장 먼저 실행이되는 entry point이다.)
	* main메소드보다 우선하는 것은 오직 static블럭 뿐이다. -- 따라서 남발은 자제해야함. 메모리 오버플로우 위험.
	*/
	public static void main(String[] args) {
		int 			port	= 1996;		//포트번호 지정
		boolean			isFlag	= false;	//boolean의 default는 false	//서버를 탈출시킬 수 있는 수단이다.
		ServerSocket	server	= null;		//기다림....언제까지? 클라이언트가 접속할 때까지 (ex- new Socket("192.168.0.23", 3000))
		Socket client = null;
		try {
			server = new ServerSocket(port);
		} catch (Exception e) {
		}
		System.out.println("TimeServer Started successfully...");
		while(!isFlag) {					//while(true) NO!!!!!!!!!! 위험해!! 무한루프에 빠질 수 있다.
			try {
				//클라이언트가 올 때까지 .....기다리는 중...기둘기둘......
//				TimeServer ts = new TimeServer(client);			분석!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
//				ts.start();	//여기서(이 시점에서) run메소드가 호출된다.		분석!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
				client = server.accept();
				TimeServer ts = new TimeServer(client);
				ts.start();
//				initDisplay();	//이 위치에 있으면 영원히 세상 구경 못 할 수도 있다. 위치와 순서, (화면)-input-operation-output구조에 유의해야 한다.
				System.out.println("New Client connected..."+client.toString()+"\n"); //"\n"은 줄바꿈
			} catch (Exception e) {
			}
		}
	}

}

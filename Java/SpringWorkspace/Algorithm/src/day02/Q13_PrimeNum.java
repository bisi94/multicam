package day02;
//�Ҽ� ���ϱ�
//�Ҽ�: 1�� �ڽ����θ� ���������� ��
//1 ~ 1000������ ���� �� �Ҽ��� ���� ����ϼ���
public class Q13_PrimeNum {

	public static void getPrime() {
		int cnt=0;
		for(int i=2;i<=1000;i++) {
			int num;
			for(num=2;num<=i;num++) {
				cnt++;
				if(i%num==0) break;
			}
			if(num==i) {
				System.out.println(i);
			}
		}//for-------------------
		System.out.println("����Ƚ��:"+cnt);
		
	}
	
	public static void main(String[] args) {
		getPrime();
	}
}

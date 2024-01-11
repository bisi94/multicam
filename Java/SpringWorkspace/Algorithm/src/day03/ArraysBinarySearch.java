package day03;
import java.util.*;
public class ArraysBinarySearch {
	
	static Scanner sc=new Scanner(System.in);
	
	public static void floatBinarySearch() {
		float[] arr= {1.1f, 2.1f, 0.4f, 'A', 'a', 30e-1f, -7.4f};
		System.out.println(Arrays.toString(arr));
		System.out.println("---오름차순 정렬 후-----------");
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		
		System.out.println("검색할 실수값을 입력하세요: ");
		float key=sc.nextFloat();
		int idx=Arrays.binarySearch(arr, key);
		if(idx<0) {
			System.out.println("idx: "+idx);
			System.out.println("검색한 "+key+"값은 배열에 없습니다");
		}else {
			System.out.println("검색한 "+key+"값은 오름차순 정렬된 배열 "+idx+"번 인덱스에 있습니다");
		}
	}//------------------------------------

	public static void stringBinarySearch() {
		/*String클래스가 Comparable인터페이스(compareTo()메서드를 구현하고 있음)를 상속받아 구현하고 있기 때문에 정렬이된다. 
		 * => 정렬이 되면 이진검색 가능하다.
		 * */
		String[] str= {
				"instanceof", "int",          "interface", "long",       "native",
	            "switch",     "synchronized", "this",      "throw",      "throws",
	            "transient",  "try",          "void",      "volatile",   "while",
	            "new",        "package",      "private",   "protected",  "public",
				"abstract",   "assert",       "boolean",   "break",      "byte",
	            "case",       "catch",        "char",      "class",      "const",
				"continue",   "default",      "do",        "double",     "else",
	            "enum",       "extends",      "final",     "finally",    "float",
	            "for",        "goto",         "if",        "implements", "import",
	            "return",     "short",        "static",    "strictfp",   "super"
		};
		
		System.out.println("---오름차순 정렬 전-----------");
		System.out.println(Arrays.toString(str));
		
		System.out.println("검색할 키워드를 입력하세요: ");
		String key=sc.next();

		System.out.println("---오름차순 정렬 후-----------");
		Arrays.sort(str);
		System.out.println(Arrays.toString(str));
		
		int idx=Arrays.binarySearch(str, key);
		if(idx<0) {
			System.out.println("idx: "+idx);
			System.out.println("검색한 "+key+"값은 배열에 없습니다");
		}else {
			System.out.println("검색한 "+key+"값은 오름차순 정렬된 배열 "+idx+"번 인덱스에 있습니다");
		}
		
	}//----------------------------------------------
	
	
	public static void stringBinarySearch1() {
		/*String클래스가 Comparable인터페이스(compareTo()메서드를 구현하고 있음)를 상속받아 구현하고 있기 때문에 정렬이된다. 
		 * => 정렬이 되면 이진검색 가능하다.
		 * */
		String[] str= {
				"instanceof", "int",          "interface", "long",       "native",
	            "switch",     "synchronized", "this",      "throw",      "throws",
	            "transient",  "try",          "void",      "volatile",   "while",
	            "new",        "package",      "private",   "protected",  "public",
				"abstract",   "assert",       "boolean",   "break",      "byte",
	            "case",       "catch",        "char",      "class",      "const",
				"continue",   "default",      "do",        "double",     "else",
	            "enum",       "extends",      "final",     "finally",    "float",
	            "for",        "goto",         "if",        "implements", "import",
	            "return",     "short",        "static",    "strictfp",   "super"
		};
		
		System.out.println("검색할 키워드를 입력하세요: ");
		String key=sc.next();
		Arrays.sort(str);
		System.out.println("---오름차순 정렬 후-----------");
		System.out.println(Arrays.toString(str));
		
		int idx=Arrays.binarySearch(str, key);
		System.out.println("idx: "+idx);
		
	}//---------------------------------------
	
	
	public static void main(String[] args) {
		//floatBinarySearch();
		//stringBinarySearch1();
		//memberBinarySearch();
		empBinarySearch();
	}

	private static void empBinarySearch() {
		//1. Emp객체 4개 생성해서 배열에 저장하세요
		Emp e1=new Emp("김일번", 190, 2600);
		Emp e2=new Emp("김이번", 170, 8600);
		Emp e3=new Emp("김삼번", 160, 3600);
		Emp e4=new Emp("김사번", 180, 4600);
		
		//2. 배열을 출력해보기
		Emp emps[]= {e1, e2, e3, e4};
		System.out.println(Arrays.toString(emps));
		System.out.println("------sort() 이후--------");
		System.out.println();
		//3. HeightOrderComparator객체 생성하기
		final HeightOrderComparator heightComp=new HeightOrderComparator();
		
		//4. Arrays.sort()로 1번 배열 정렬하기
		Arrays.sort(emps, heightComp);//키 오름차순으로 정렬
		
		//5.  배열을 출력해보기
		System.out.println(">> 키로 정렬(asc) <<");
		System.out.println(Arrays.toString(emps));
		System.out.println();
		
		//6. 급여 내림차순으로 다시 정렬해서 출력해보세요
		final SalaryOrderComparator salComp=new SalaryOrderComparator();
		Arrays.sort(emps, salComp);//sal 내림차순으로 정렬
		System.out.println(">> sal로 정렬(desc) <<");
		System.out.println(Arrays.toString(emps));
		
		System.out.println("급여가 얼마인 사원정보를 찾고 있나요?");
		int findSalary=sc.nextInt();
		
		//Arrays binarySearch() 이용해서 검색후 검색한 사람의 모든 정보 출력하세요
		int idx=Arrays.binarySearch(emps, new Emp("",0,findSalary),salComp);
		if(idx<0) {
			System.out.println(findSalary+"의 급여를 받는 사원은 없어요");
		}else {
			System.out.println(idx+"인덱스에 있어요");
			System.out.println(emps[idx]);
		}
	}

	public static void memberBinarySearch() {
		//Member 4개 생성해서 배열에 저장하기
		Member m1=new Member("홍길동", 30, 160);
		Member m2=new Member("홍룰루", 24, 181);
		Member m3=new Member("김길동", 21, 167);
		Member m4=new Member("박남동", 26, 172);
		Member m5=new Member("홍룰루", 24, 181);
		
		//배열에 저장한 뒤 배열을 출력하기
		Member[] users= {m1, m2, m3, m4, m5};
		System.out.println(Arrays.toString(users));
		
		//Arrays.sort()로 정렬하고 배열을 출력하기 --> Member의 compareTo()에서 구현
		Arrays.sort(users);
		System.out.println("--------sort() 이후 Member------------");
		System.out.println(Arrays.toString(users));
		//boolean equals(Object obj) : 주소값 비교
		System.out.println("m2: "+m2);
		System.out.println("m5: "+m5);
		
		System.out.println("m2.equals(m5) : "+m2.equals(m5)); //false heap에 올라가는 메모리의 주소값이 다름
		//m2와 m5의 내용비교를 하고싶다면 ==> equals()를 오버라이딩 해야 한다

		System.out.println("검색할 회원의 나이는 : ");
		int findAge=sc.nextInt();
		//user에서 findAge회원이 나이 오름차순으로 정렬한 배열 기준으로
		//몇번째 인덱스에 있는지 출력하세요
		int idx=Arrays.binarySearch(users, new Member("",findAge,0));
		if(idx<0) {
			System.out.println(findAge+"세 회원은 없습니다");
		}else {
			System.out.println(findAge+"세 회원을 찾았습니다 : idx = "+idx);
			System.out.println(users[idx]);
		}
	}
}











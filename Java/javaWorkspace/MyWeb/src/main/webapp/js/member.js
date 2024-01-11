/**
 * 
 */
let win=null; //전역변수
function editMember(no){
	/*alert(no);*/
	//팝업창 띄우기
	let url="member/edit.jsp?no="+no;//회원정보 수정 폼 페이지
	
	win=window.open(url,"edit","width=700, height=700, left=300, top=200");
	
}//-------------------
function winClose(){
	self.close();//팝업창 닫기
}
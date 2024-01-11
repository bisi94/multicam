package common.controller;

abstract public class AbstractAction implements Action{
	
	private String viewPage; //보여줄 뷰 페이지(jsp)이름
	private boolean isRedirect=false;
	//true면 redirect이동, false이면 forward이동할 예정

	
	//execute() 추상메소드를 가지고 있음
	
	//setter, getter
	
	public String getViewPage() {
		return viewPage;
	}
	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
}

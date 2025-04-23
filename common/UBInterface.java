package singleBathroom;

public interface UBInterface {
	public void enterMan(int id);
	public void enterWoman(int id);
	public void enterChild(int id);
	public void enterJanitor();
	
	public void exitMan(int id);
	public void exitWoman(int id);
	public void exitChild(int id);
	public void exitJanitor();

}

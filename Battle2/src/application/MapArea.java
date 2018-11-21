package application;

public class MapArea {
	private String Name="";
	private int ID;
	private int Far;
	private boolean Flag=false;
	private String comment="";

	public MapArea(String Name, int ID, int Far,boolean Flag,String comment){
		this.Name=Name;
		this.ID=ID;
		this.Far=Far;
		this.Flag=Flag;
		this.comment=comment;
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getFar() {
		return Far;
	}
	public void setFar(int far) {
		Far = far;
	}
	public boolean isFlag() {
		return Flag;
	}
	public void setFlag(boolean flag) {
		Flag = flag;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}

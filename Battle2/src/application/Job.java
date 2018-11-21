package application;

public class Job {
	private String Name="";
	private int ID;
	private int maxjobEXP;
	private int jobEXP;
	private double[] statusrate=new double[6];//maxHP,maxSP,power,defense,mind,speed

	private String comment="";

	public Job(String name,int ID,String comment,double... statusrate){
		this.setName(name);
		this.setID(ID);
		for(int i=0;i<statusrate.length;i++){
			//System.out.println(statusrate[i]);
			this.statusrate[i]=statusrate[i];
		}
		this.comment=comment;
	}

	public int getID(){
		return ID;
	}

	public void setID(int iD){
		this.ID=iD;
	}

	public String getName(){
		return Name;
	}

	public void setName(String Name){
		this.Name=Name;
	}

	public String getComment(){
		return comment;
	}

	public void setComment(String comment){
		this.comment=comment;
	}

	public int getMaxjobEXP() {
		return maxjobEXP;
	}

	public void setMaxjobEXP(int maxjobEXP) {
		this.maxjobEXP = maxjobEXP;
	}

	public int getJobEXP() {
		return jobEXP;
	}

	public void setJobEXP(int jobEXP) {
		this.jobEXP = jobEXP;
	}
}

package application;


public class Battler implements Cloneable{
	private String Name="";
	private int ID=999;
	private int LV=0;
	private int BP=20;
	private int gender=999;
	private Job job;
	private int maxEXP=10;
	private int EXP=0;

	private int[] status=new int[8];
	/*
	private int maxHP=0;
	private int HP=0;
	private int maxSP=0;
	private int SP=0;
	private int power=0;
	private int defense=0;
	private int mind=0;
	private int speed=0;
	*/

	public Battler(){

	}

	public Battler(String Name,Job job,int maxHP,int maxSP,int power,int defense, int mind, int speed){
		this.Name=Name;
		this.job=job;
		this.status[0]=maxHP;
		this.status[1]=maxHP;
		this.status[2]=maxSP;
		this.status[3]=maxSP;
		this.status[4]=power;
		this.status[5]=defense;
		this.status[6]=mind;
		this.status[7]=speed;
	}
	public Battler(String Name,int ID,Job job,int maxHP,int maxSP,int power,int defense, int mind, int speed){
		this.Name=Name;
		this.ID=ID;
		this.job=job;
		this.status[0]=maxHP;
		this.status[1]=maxHP;
		this.status[2]=maxSP;
		this.status[3]=maxSP;
		this.status[4]=power;
		this.status[5]=defense;
		this.status[6]=mind;
		this.status[7]=speed;
	}

	protected String getName(){
		return this.Name;
	}
	protected void setName(String newName){
		this.Name=newName;
	}
	protected int getID(){
		return this.ID;
	}
	protected void setID(int newID){
		this.ID=newID;
	}
	protected int getLV(){
		return this.LV;
	}
	protected void setLV(int newLV){
		this.LV=newLV;
	}
	protected int getgender(){
		return this.gender;
	}
	protected void setgender(int newgender){
		this.gender=newgender;
	}
	protected Job getjob(){
		return this.job;
	}
	protected void setjob(Job newjob){
		this.job=newjob;
	}
	protected int getanystatus(int n){//maxHP,HP,maxSP,SP,power,defense,mind,speed
		return this.status[n];
	}
	protected void setanystatus(int n,int newValue){
		this.status[n]=newValue;
	}
	protected int[] getstatus(){
		return this.status;
	}
	protected void setstatus(int[] newstatus){
		for(int n:newstatus){
			this.status[n]=newstatus[n];
		}
	}
	public int getMaxEXP() {
		return maxEXP;
	}
	public void setMaxEXP(int maxEXP) {
		this.maxEXP = maxEXP;
	}
	public int getEXP() {
		return EXP;
	}
	public void setEXP(int eXP) {
		EXP = eXP;
	}
	public int getBP() {
		return BP;
	}
	public void setBP(int bP) {
		BP = bP;
	}


	@Override
	public Battler clone(){
		try{
			Battler result=(Battler)super.clone();
			result.status=this.status.clone();
			return result;
		}catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}

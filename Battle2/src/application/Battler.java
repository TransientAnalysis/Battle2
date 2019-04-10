package application;


public class Battler implements Cloneable{
	private String name="";
	private int ID=999;
	private int LV=1;
	private int BP=20;
	private int gender=999;
	private Job job;
	private int maxEXP=10;
	private int EXP=0;

	private int[] status=new int[8];

	public Battler(){

	}

	public Battler(String name,Job job,int maxHP,int maxSP,int power,int defense, int mind, int speed){
		this.name=name;
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

	public Battler(String name, int ID, Job job, int maxHP, int maxSP, int power, int defense, int mind, int speed, int EXP){
		this.name=name;
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
		this.EXP=EXP;
	}

	protected String getName(){
		return this.name;
	}
	protected void setName(String name){
		this.name=name;
	}
	protected int getID(){
		return this.ID;
	}
	protected void setID(int ID){
		this.ID=ID;
	}
	protected int getLV(){
		return this.LV;
	}
	protected void setLV(int LV){
		this.LV=LV;
	}
	protected void addLV(int num) {
		LV+=num;
	}
	protected int getgender(){
		return this.gender;
	}
	protected void setgender(int gender){
		this.gender=gender;
	}
	protected Job getjob(){
		return this.job;
	}
	protected void setjob(Job job){
		this.job=job;
	}
	protected int getanystatus(int n){//maxHP,HP,maxSP,SP,power,defense,mind,speed
		return this.status[n];
	}
	protected void setanystatus(int n, int Value){
		this.status[n]=Value;
	}
	protected int[] getstatus(){
		return this.status;
	}
	protected void setstatus(int[] status){
		for(int n:status){
			this.status[n]=status[n];
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
	public void setEXP(int EXP) {
		this.EXP = EXP;
	}
	public void addEXP(int EXP) {
		int dlv=0;
		this.EXP+=EXP;
		while(this.EXP>=maxEXP) {
			setEXP(this.EXP-maxEXP);
			dlv++;
			maxEXP+=3*(LV+1)*LV;//必要経験値量上昇 MAX+X*(LV+2)*(LV+1) 0 6  1 18  2 36  3 60  4 90  5 126  6 168  7 216
		}
		if(dlv>0) {
			System.out.println(name+"のLVが"+dlv+"上がったよ");
			addLV(dlv);
			BP+=dlv;
		}
	}
	public int getBP() {
		return BP;
	}
	public void setBP(int BP) {
		this.BP = BP;
	}

	@Override
	public String toString() {
		return "["+this.name+", exp:"+this.EXP+"]";
	}

	@Override
	public Battler clone(){
		try{
			Battler result=(Battler)super.clone();
			result.status=this.status.clone();
			result.EXP=this.EXP;
			return result;
		}catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}

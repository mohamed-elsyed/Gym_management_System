package gym;

import java.sql.Date;

public class Gym {
	private String name ;
	private	int age ;
	private	float height;
	private	float weight;
	private	Date subscriptionStartDate;
	private	Date subscriptionEndDate;
	
	public Gym(String name,int age ,float height,float weight,String subscriptionStartDate,String subscriptionEndDate)
	{
	 this.name=name;
     this.age =age;
     this.height=height;
     this.weight=weight;
     this.subscriptionStartDate=Date.valueOf(subscriptionStartDate);
     this.subscriptionEndDate=Date.valueOf(subscriptionEndDate);
		
	}
	public Gym() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public Date getSubscriptionStartDate() {
		return subscriptionStartDate;
	}

	public void setSubscriptionStartDate(Date subscriptionStartDate) {
		this.subscriptionStartDate = subscriptionStartDate;
	}

	public Date getSubscriptionEndDate() {
		return subscriptionEndDate;
	}

	public void setSubscriptionEndDate(Date subscriptionEndDate) {
		this.subscriptionEndDate = subscriptionEndDate;
	}

	@Override
	public String toString() {
		return " name= " + name + "\n"
				+ " age= " + age + "\n"+
				" height= " + height + "\n"+
				" weight= " + weight +"\n"
				+ " subscriptionStartDate= " + subscriptionStartDate +"\n"+ 
				" subscriptionEndDate= " + subscriptionEndDate
				;
	}
	
	 
}

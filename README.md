
## Contextualization 
There is several kinds of Updates to be performed in an user register (personal data update, adress data update, etc). Depending on what step of the business rule the user is, one of these updates will be required.

## Applied Solution - Working With Enums And Spring Features
1. Create an interface (UpdateInterfaceAction) that must be inherited by each one of the different kind of updates 
2. Implement the interface in the specialized classes as components.
3.  Create an Enum that will link the business rule step with an **update action**. The enum will store the **class reference** (classeOfUpdateAction) and the **object reference** (myUpdate) in two variable.
4. Using Spring feature **@PostConstruct**, assign to the **object reference**, inside the enum, using **ApplicationContext** the beans created in the second step using the class reference (classeOfUpdateAction).

```java
public enum MyEnumUpdateByStep {
	STEP_1(),

	STEP_2();

	private String step;
	private UpdateInterfaceAction myUpdate
	
	private Class<? extends UpdateInterfaceAction> classeOfUpdateAction;
	
	MyEnumUpdateByStep (String step, Class<? extends UpdateInterfaceAction> classeOfUpdateAction)

    public void setUpdateAction(UpdateInterfaceAction upAction) {  
        this.myUpdate= upAction;  
    }

	//Using Spring to assign the correct UpdateInterfaceAction to my variable
	
	public static class MyEnumUpdateByStepInjector {
		ApplicationContext context;

		public MyEnumUpdateByStepInjector (ApplicationContext context){
			this.context = context;
		}
		
		@PostConstruct
		public void injectUpAction(){
		//for (act: EnumSet.allOf(Enum.class) {
		// setUpdateAction(context.Getbean(Class)
		//}
		}
	}
}
```



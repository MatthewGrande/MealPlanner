package mealPlanner.model;
import java.util.*;
import java.sql.Date;

// line 11 "model.ump"
// line 70 "model.ump"
public class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private int goalCalorie;

  //User Associations
  private MealPlannerApp application;
  private List<DietType> dietType;
  private Day currentDay;
  private List<Day> pastDays;
  private List<OwnedIngredient> ownedIngredients;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(int aGoalCalorie, MealPlannerApp aApplication)
  {
    goalCalorie = aGoalCalorie;
    boolean didAddApplication = setApplication(aApplication);
    if (!didAddApplication)
    {
      throw new RuntimeException("Unable to create user due to application. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    dietType = new ArrayList<DietType>();
    pastDays = new ArrayList<Day>();
    ownedIngredients = new ArrayList<OwnedIngredient>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGoalCalorie(int aGoalCalorie)
  {
    boolean wasSet = false;
    goalCalorie = aGoalCalorie;
    wasSet = true;
    return wasSet;
  }

  public int getGoalCalorie()
  {
    return goalCalorie;
  }
  /* Code from template association_GetOne */
  public MealPlannerApp getApplication()
  {
    return application;
  }
  /* Code from template association_GetMany */
  public DietType getDietType(int index)
  {
    DietType aDietType = dietType.get(index);
    return aDietType;
  }

  public List<DietType> getDietType()
  {
    List<DietType> newDietType = Collections.unmodifiableList(dietType);
    return newDietType;
  }

  public int numberOfDietType()
  {
    int number = dietType.size();
    return number;
  }

  public boolean hasDietType()
  {
    boolean has = dietType.size() > 0;
    return has;
  }

  public int indexOfDietType(DietType aDietType)
  {
    int index = dietType.indexOf(aDietType);
    return index;
  }
  /* Code from template association_GetOne */
  public Day getCurrentDay()
  {
    return currentDay;
  }

  public boolean hasCurrentDay()
  {
    boolean has = currentDay != null;
    return has;
  }
  /* Code from template association_GetMany */
  public Day getPastDay(int index)
  {
    Day aPastDay = pastDays.get(index);
    return aPastDay;
  }

  public List<Day> getPastDays()
  {
    List<Day> newPastDays = Collections.unmodifiableList(pastDays);
    return newPastDays;
  }

  public int numberOfPastDays()
  {
    int number = pastDays.size();
    return number;
  }

  public boolean hasPastDays()
  {
    boolean has = pastDays.size() > 0;
    return has;
  }

  public int indexOfPastDay(Day aPastDay)
  {
    int index = pastDays.indexOf(aPastDay);
    return index;
  }
  /* Code from template association_GetMany */
  public OwnedIngredient getOwnedIngredient(int index)
  {
    OwnedIngredient aOwnedIngredient = ownedIngredients.get(index);
    return aOwnedIngredient;
  }

  public List<OwnedIngredient> getOwnedIngredients()
  {
    List<OwnedIngredient> newOwnedIngredients = Collections.unmodifiableList(ownedIngredients);
    return newOwnedIngredients;
  }

  public int numberOfOwnedIngredients()
  {
    int number = ownedIngredients.size();
    return number;
  }

  public boolean hasOwnedIngredients()
  {
    boolean has = ownedIngredients.size() > 0;
    return has;
  }

  public int indexOfOwnedIngredient(OwnedIngredient aOwnedIngredient)
  {
    int index = ownedIngredients.indexOf(aOwnedIngredient);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setApplication(MealPlannerApp aApplication)
  {
    boolean wasSet = false;
    if (aApplication == null)
    {
      return wasSet;
    }

    MealPlannerApp existingApplication = application;
    application = aApplication;
    if (existingApplication != null && !existingApplication.equals(aApplication))
    {
      existingApplication.removeUser(this);
    }
    application.addUser(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfDietTypeValid()
  {
    boolean isValid = numberOfDietType() >= minimumNumberOfDietType();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDietType()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public DietType addDietType(String aType)
  {
    DietType aNewDietType = new DietType(aType, this);
    return aNewDietType;
  }

  public boolean addDietType(DietType aDietType)
  {
    boolean wasAdded = false;
    if (dietType.contains(aDietType)) { return false; }
    User existingUser = aDietType.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);

    if (isNewUser && existingUser.numberOfDietType() <= minimumNumberOfDietType())
    {
      return wasAdded;
    }
    if (isNewUser)
    {
      aDietType.setUser(this);
    }
    else
    {
      dietType.add(aDietType);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDietType(DietType aDietType)
  {
    boolean wasRemoved = false;
    //Unable to remove aDietType, as it must always have a user
    if (this.equals(aDietType.getUser()))
    {
      return wasRemoved;
    }

    //user already at minimum (1)
    if (numberOfDietType() <= minimumNumberOfDietType())
    {
      return wasRemoved;
    }

    dietType.remove(aDietType);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addDietTypeAt(DietType aDietType, int index)
  {  
    boolean wasAdded = false;
    if(addDietType(aDietType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDietType()) { index = numberOfDietType() - 1; }
      dietType.remove(aDietType);
      dietType.add(index, aDietType);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDietTypeAt(DietType aDietType, int index)
  {
    boolean wasAdded = false;
    if(dietType.contains(aDietType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDietType()) { index = numberOfDietType() - 1; }
      dietType.remove(aDietType);
      dietType.add(index, aDietType);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDietTypeAt(aDietType, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setCurrentDay(Day aNewCurrentDay)
  {
    boolean wasSet = false;
    currentDay = aNewCurrentDay;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPastDays()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Day addPastDay(Date aDate, int aCalorieCount)
  {
    return new Day(aDate, aCalorieCount, this);
  }

  public boolean addPastDay(Day aPastDay)
  {
    boolean wasAdded = false;
    if (pastDays.contains(aPastDay)) { return false; }
    User existingUser = aPastDay.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aPastDay.setUser(this);
    }
    else
    {
      pastDays.add(aPastDay);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePastDay(Day aPastDay)
  {
    boolean wasRemoved = false;
    //Unable to remove aPastDay, as it must always have a user
    if (!this.equals(aPastDay.getUser()))
    {
      pastDays.remove(aPastDay);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPastDayAt(Day aPastDay, int index)
  {  
    boolean wasAdded = false;
    if(addPastDay(aPastDay))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPastDays()) { index = numberOfPastDays() - 1; }
      pastDays.remove(aPastDay);
      pastDays.add(index, aPastDay);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePastDayAt(Day aPastDay, int index)
  {
    boolean wasAdded = false;
    if(pastDays.contains(aPastDay))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPastDays()) { index = numberOfPastDays() - 1; }
      pastDays.remove(aPastDay);
      pastDays.add(index, aPastDay);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPastDayAt(aPastDay, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOwnedIngredients()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OwnedIngredient addOwnedIngredient(int aAmount, Ingredient aIngredient)
  {
    return new OwnedIngredient(aAmount, aIngredient, this);
  }

  public boolean addOwnedIngredient(OwnedIngredient aOwnedIngredient)
  {
    boolean wasAdded = false;
    if (ownedIngredients.contains(aOwnedIngredient)) { return false; }
    User existingUser = aOwnedIngredient.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aOwnedIngredient.setUser(this);
    }
    else
    {
      ownedIngredients.add(aOwnedIngredient);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOwnedIngredient(OwnedIngredient aOwnedIngredient)
  {
    boolean wasRemoved = false;
    //Unable to remove aOwnedIngredient, as it must always have a user
    if (!this.equals(aOwnedIngredient.getUser()))
    {
      ownedIngredients.remove(aOwnedIngredient);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOwnedIngredientAt(OwnedIngredient aOwnedIngredient, int index)
  {  
    boolean wasAdded = false;
    if(addOwnedIngredient(aOwnedIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOwnedIngredients()) { index = numberOfOwnedIngredients() - 1; }
      ownedIngredients.remove(aOwnedIngredient);
      ownedIngredients.add(index, aOwnedIngredient);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOwnedIngredientAt(OwnedIngredient aOwnedIngredient, int index)
  {
    boolean wasAdded = false;
    if(ownedIngredients.contains(aOwnedIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOwnedIngredients()) { index = numberOfOwnedIngredients() - 1; }
      ownedIngredients.remove(aOwnedIngredient);
      ownedIngredients.add(index, aOwnedIngredient);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOwnedIngredientAt(aOwnedIngredient, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    MealPlannerApp placeholderApplication = application;
    this.application = null;
    if(placeholderApplication != null)
    {
      placeholderApplication.removeUser(this);
    }
    for(int i=dietType.size(); i > 0; i--)
    {
      DietType aDietType = dietType.get(i - 1);
      aDietType.delete();
    }
    currentDay = null;
    while (pastDays.size() > 0)
    {
      Day aPastDay = pastDays.get(pastDays.size() - 1);
      aPastDay.delete();
      pastDays.remove(aPastDay);
    }
    
    for(int i=ownedIngredients.size(); i > 0; i--)
    {
      OwnedIngredient aOwnedIngredient = ownedIngredients.get(i - 1);
      aOwnedIngredient.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "goalCalorie" + ":" + getGoalCalorie()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "application = "+(getApplication()!=null?Integer.toHexString(System.identityHashCode(getApplication())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "currentDay = "+(getCurrentDay()!=null?Integer.toHexString(System.identityHashCode(getCurrentDay())):"null");
  }
}
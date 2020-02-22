/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4785.04bd3fea6 modeling language!*/

package mealPlanner.model;
import java.util.*;
import java.sql.Date;

// line 13 "model.ump"
// line 72 "model.ump"
public class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String username;
  private String password;
  private int goalCalorie;

  //User Associations
  private List<DietType> dietType;
  private List<Day> pastDays;
  private Day currentDay;
  private List<OwnedIngredient> ownedIngredients;
  private List<Recipe> savedRecipes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aUsername, String aPassword, int aGoalCalorie, Day aCurrentDay)
  {
    username = aUsername;
    password = aPassword;
    goalCalorie = aGoalCalorie;
    dietType = new ArrayList<DietType>();
    pastDays = new ArrayList<Day>();
    if (!setCurrentDay(aCurrentDay))
    {
      throw new RuntimeException("Unable to create User due to aCurrentDay. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    ownedIngredients = new ArrayList<OwnedIngredient>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    username = aUsername;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setGoalCalorie(int aGoalCalorie)
  {
    boolean wasSet = false;
    goalCalorie = aGoalCalorie;
    wasSet = true;
    return wasSet;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }

  public int getGoalCalorie()
  {
    return goalCalorie;
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
  
  public Recipe getSavedRecipe(int index)
  {
    Recipe aRecipe = savedRecipes.get(index);
    return aRecipe;
  }

  public List<Recipe> getSavedRecipes()
  {
    List<Recipe> newSavedRecipes = Collections.unmodifiableList(savedRecipes);
    return newSavedRecipes;
  }
  public int numberOfSavedRecipes()
  {
    int number = savedRecipes.size();
    return number;
  }

  public boolean hasSavedRecipes()
  {
    boolean has = savedRecipes.size() > 0;
    return has;
  }

  public int indexOfSavedRecipe(Recipe aSavedRecipe)
  {
    int index = savedRecipes.indexOf(aSavedRecipe);
    return index;
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
  /* Code from template association_GetOne */
  public Day getCurrentDay()
  {
    return currentDay;
  }
  /* Code from template association_GetMany */
  public OwnedIngredient getOwnedIngredient(int index)
  {
    OwnedIngredient aOwnedIngredient = ownedIngredients.get(index);
    return aOwnedIngredient;
  }

  public OwnedIngredient getOwnedIngredient(String ingredient)
  {
    List<OwnedIngredient> aOwnedIngredient = this.getOwnedIngredients();
    for (OwnedIngredient o: aOwnedIngredient) {
    	if (o.getIngredient().getName().equals(ingredient)) {
    		return o;
    	}
    }
    return null;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDietType()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addDietType(DietType aDietType)
  {
    boolean wasAdded = false;
    if (dietType.contains(aDietType)) { return false; }
    dietType.add(aDietType);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDietType(DietType aDietType)
  {
    boolean wasRemoved = false;
    if (dietType.contains(aDietType))
    {
      dietType.remove(aDietType);
      wasRemoved = true;
    }
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
  public boolean addSavedRecipe(Recipe aSavedRecipe)
  {
    boolean wasAdded = false;
    if (savedRecipes.contains(aSavedRecipe)) { return false; }
    savedRecipes.add(aSavedRecipe);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSavedRecipe(Recipe aSavedRecipe)
  {
    boolean wasRemoved = false;
    if (savedRecipes.contains(aSavedRecipe))
    {
      savedRecipes.remove(aSavedRecipe);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPastDays()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addPastDay(Day aPastDay)
  {
    boolean wasAdded = false;
    if (pastDays.contains(aPastDay)) { return false; }
    pastDays.add(aPastDay);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePastDay(Day aPastDay)
  {
    boolean wasRemoved = false;
    if (pastDays.contains(aPastDay))
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
  /* Code from template association_SetUnidirectionalOne */
  public boolean setCurrentDay(Day aNewCurrentDay)
  {
    boolean wasSet = false;
    if (aNewCurrentDay != null)
    {
      currentDay = aNewCurrentDay;
      wasSet = true;
    }
    return wasSet;
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
    if (ownedIngredients.contains(aOwnedIngredient)) {
    	return false;
    	}
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
    dietType.clear();
    pastDays.clear();
    currentDay = null;
    for(int i=ownedIngredients.size(); i > 0; i--)
    {
      OwnedIngredient aOwnedIngredient = ownedIngredients.get(i - 1);
      aOwnedIngredient.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "," +
            "goalCalorie" + ":" + getGoalCalorie()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "currentDay = "+(getCurrentDay()!=null?Integer.toHexString(System.identityHashCode(getCurrentDay())):"null");
  }
}
package mealPlanner.model;



import java.util.*;

// line 23 "model.ump"
// line 76 "model.ump"
public class Recipe
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Recipe Attributes
  private int calorieCountPerServing;

  //Recipe Associations
  private MealPlannerApp application;
  private List<DietType> dietType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Recipe(int aCalorieCountPerServing, MealPlannerApp aApplication)
  {
    calorieCountPerServing = aCalorieCountPerServing;
    boolean didAddApplication = setApplication(aApplication);
    if (!didAddApplication)
    {
      throw new RuntimeException("Unable to create gameSessionFactory due to application. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    dietType = new ArrayList<DietType>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCalorieCountPerServing(int aCalorieCountPerServing)
  {
    boolean wasSet = false;
    calorieCountPerServing = aCalorieCountPerServing;
    wasSet = true;
    return wasSet;
  }

  public int getCalorieCountPerServing()
  {
    return calorieCountPerServing;
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
      existingApplication.removeGameSessionFactory(this);
    }
    application.addGameSessionFactory(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDietType()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addDietType(DietType aDietType)
  {
    boolean wasAdded = false;
    if (dietType.contains(aDietType)) { return false; }
    dietType.add(aDietType);
    if (aDietType.indexOfRecipe(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aDietType.addRecipe(this);
      if (!wasAdded)
      {
        dietType.remove(aDietType);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeDietType(DietType aDietType)
  {
    boolean wasRemoved = false;
    if (!dietType.contains(aDietType))
    {
      return wasRemoved;
    }

    int oldIndex = dietType.indexOf(aDietType);
    dietType.remove(oldIndex);
    if (aDietType.indexOfRecipe(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aDietType.removeRecipe(this);
      if (!wasRemoved)
      {
        dietType.add(oldIndex,aDietType);
      }
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

  public void delete()
  {
    MealPlannerApp placeholderApplication = application;
    this.application = null;
    if(placeholderApplication != null)
    {
      placeholderApplication.removeGameSessionFactory(this);
    }
    ArrayList<DietType> copyOfDietType = new ArrayList<DietType>(dietType);
    dietType.clear();
    for(DietType aDietType : copyOfDietType)
    {
      aDietType.removeRecipe(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "calorieCountPerServing" + ":" + getCalorieCountPerServing()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "application = "+(getApplication()!=null?Integer.toHexString(System.identityHashCode(getApplication())):"null");
  }
}
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4785.04bd3fea6 modeling language!*/

package mealPlanner.model;
import java.util.*;

// line 27 "model.ump"
// line 82 "model.ump"
public class Recipe
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Recipe Attributes
  private int calorieCountPerServing;
  private String name;

  //Recipe Associations
  private List<DietType> dietType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Recipe(String aName, int aCalorieCountPerServing)
  {
	name = aName;
    calorieCountPerServing = aCalorieCountPerServing;
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

  public Boolean setName(String aName)
  {
	  boolean wasSet = false;
	    name = aName;
	    wasSet = true;
	    return wasSet;
  }
  
  public String getName()
  {
    return name;
  }
  
  public int getCalorieCountPerServing()
  {
    return calorieCountPerServing;
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

  public void delete()
  {
    dietType.clear();
  }


  public String toString()
  {
    return super.toString() + "["+
            "calorieCountPerServing" + ":" + getCalorieCountPerServing()+ "]";
  }
}
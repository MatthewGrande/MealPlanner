package mealPlanner.model;

import java.util.*;

// line 7 "model.ump"
// line 65 "model.ump"
public class MealPlannerApp
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MealPlannerApp Associations
  private List<User> user;
  private List<Recipe> recipies;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MealPlannerApp()
  {
    user = new ArrayList<User>();
    recipies = new ArrayList<Recipe>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = user.get(index);
    return aUser;
  }

  public List<User> getUser()
  {
    List<User> newUser = Collections.unmodifiableList(user);
    return newUser;
  }

  public int numberOfUser()
  {
    int number = user.size();
    return number;
  }

  public boolean hasUser()
  {
    boolean has = user.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = user.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetMany */
  public Recipe getrecipies(int index)
  {
    Recipe arecipies = recipies.get(index);
    return arecipies;
  }

  public List<Recipe> getrecipies()
  {
    List<Recipe> newrecipies = Collections.unmodifiableList(recipies);
    return newrecipies;
  }

  public int numberOfrecipies()
  {
    int number = recipies.size();
    return number;
  }

  public boolean hasrecipies()
  {
    boolean has = recipies.size() > 0;
    return has;
  }

  public int indexOfrecipies(Recipe arecipies)
  {
    int index = recipies.indexOf(arecipies);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUser()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public User addUser(int aGoalCalorie)
  {
    return new User(aGoalCalorie, this);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (user.contains(aUser)) { return false; }
    MealPlannerApp existingApplication = aUser.getApplication();
    boolean isNewApplication = existingApplication != null && !this.equals(existingApplication);
    if (isNewApplication)
    {
      aUser.setApplication(this);
    }
    else
    {
      user.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a application
    if (!this.equals(aUser.getApplication()))
    {
      user.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUser()) { index = numberOfUser() - 1; }
      user.remove(aUser);
      user.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(user.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUser()) { index = numberOfUser() - 1; }
      user.remove(aUser);
      user.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfrecipies()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Recipe addrecipies(int aCalorieCountPerServing)
  {
    return new Recipe(aCalorieCountPerServing, this);
  }

  public boolean addrecipies(Recipe arecipies)
  {
    boolean wasAdded = false;
    if (recipies.contains(arecipies)) { return false; }
    MealPlannerApp existingApplication = arecipies.getApplication();
    boolean isNewApplication = existingApplication != null && !this.equals(existingApplication);
    if (isNewApplication)
    {
      arecipies.setApplication(this);
    }
    else
    {
      recipies.add(arecipies);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removerecipies(Recipe arecipies)
  {
    boolean wasRemoved = false;
    //Unable to remove arecipies, as it must always have a application
    if (!this.equals(arecipies.getApplication()))
    {
      recipies.remove(arecipies);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addrecipiesAt(Recipe arecipies, int index)
  {  
    boolean wasAdded = false;
    if(addrecipies(arecipies))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfrecipies()) { index = numberOfrecipies() - 1; }
      recipies.remove(arecipies);
      recipies.add(index, arecipies);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoverecipiesAt(Recipe arecipies, int index)
  {
    boolean wasAdded = false;
    if(recipies.contains(arecipies))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfrecipies()) { index = numberOfrecipies() - 1; }
      recipies.remove(arecipies);
      recipies.add(index, arecipies);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addrecipiesAt(arecipies, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (user.size() > 0)
    {
      User aUser = user.get(user.size() - 1);
      aUser.delete();
      user.remove(aUser);
    }
    
    while (recipies.size() > 0)
    {
      Recipe arecipies = recipies.get(recipies.size() - 1);
      arecipies.delete();
      recipies.remove(arecipies);
    }
    
  }

}
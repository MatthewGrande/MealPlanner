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
  private List<Recipe> gameSessionFactory;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MealPlannerApp()
  {
    user = new ArrayList<User>();
    gameSessionFactory = new ArrayList<Recipe>();
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
  public Recipe getGameSessionFactory(int index)
  {
    Recipe aGameSessionFactory = gameSessionFactory.get(index);
    return aGameSessionFactory;
  }

  public List<Recipe> getGameSessionFactory()
  {
    List<Recipe> newGameSessionFactory = Collections.unmodifiableList(gameSessionFactory);
    return newGameSessionFactory;
  }

  public int numberOfGameSessionFactory()
  {
    int number = gameSessionFactory.size();
    return number;
  }

  public boolean hasGameSessionFactory()
  {
    boolean has = gameSessionFactory.size() > 0;
    return has;
  }

  public int indexOfGameSessionFactory(Recipe aGameSessionFactory)
  {
    int index = gameSessionFactory.indexOf(aGameSessionFactory);
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
  public static int minimumNumberOfGameSessionFactory()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Recipe addGameSessionFactory(int aCalorieCountPerServing)
  {
    return new Recipe(aCalorieCountPerServing, this);
  }

  public boolean addGameSessionFactory(Recipe aGameSessionFactory)
  {
    boolean wasAdded = false;
    if (gameSessionFactory.contains(aGameSessionFactory)) { return false; }
    MealPlannerApp existingApplication = aGameSessionFactory.getApplication();
    boolean isNewApplication = existingApplication != null && !this.equals(existingApplication);
    if (isNewApplication)
    {
      aGameSessionFactory.setApplication(this);
    }
    else
    {
      gameSessionFactory.add(aGameSessionFactory);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGameSessionFactory(Recipe aGameSessionFactory)
  {
    boolean wasRemoved = false;
    //Unable to remove aGameSessionFactory, as it must always have a application
    if (!this.equals(aGameSessionFactory.getApplication()))
    {
      gameSessionFactory.remove(aGameSessionFactory);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGameSessionFactoryAt(Recipe aGameSessionFactory, int index)
  {  
    boolean wasAdded = false;
    if(addGameSessionFactory(aGameSessionFactory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGameSessionFactory()) { index = numberOfGameSessionFactory() - 1; }
      gameSessionFactory.remove(aGameSessionFactory);
      gameSessionFactory.add(index, aGameSessionFactory);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGameSessionFactoryAt(Recipe aGameSessionFactory, int index)
  {
    boolean wasAdded = false;
    if(gameSessionFactory.contains(aGameSessionFactory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGameSessionFactory()) { index = numberOfGameSessionFactory() - 1; }
      gameSessionFactory.remove(aGameSessionFactory);
      gameSessionFactory.add(index, aGameSessionFactory);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGameSessionFactoryAt(aGameSessionFactory, index);
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
    
    while (gameSessionFactory.size() > 0)
    {
      Recipe aGameSessionFactory = gameSessionFactory.get(gameSessionFactory.size() - 1);
      aGameSessionFactory.delete();
      gameSessionFactory.remove(aGameSessionFactory);
    }
    
  }

}
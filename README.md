# Survival Simulation
## [Challenge 01 CS230]
This is a Java-based simulation that models the survival of four individuals (doctor, farmer, carpenter, and hunter) in a world affected by various disasters.
<br>The simulation can be run in two modes:

1. **Society Mode**: People work together and share resources.
2. **Anarchy Mode**: Each person acts independently for their survival.

## How It Works
- Each person has three attributes: **Health**, **Food**, and **Shelter**, all starting at 10.
- Each day, one unit of food is consumed.
- Random disasters (hurricanes, famine, disease, wolves) can negatively impact the attributes.
- Each person has a role-specific skill that positively affects one of the attributes.
- If a person's health or food reaches 0, they die.
- The simulation ends when everyone is dead or 365 days are completed.

## Skills
- **Doctor**: Increases health.
- **Farmer**: Increases food every 3 days.
- **Carpenter**: Increases shelter.
- **Hunter**: Has a 1 in 5 chance of finding food.

## Disasters
- **Hurricane**: Reduces shelter or health if no shelter.
- **Famine**: Reduces food.
- **Disease**: Reduces health.
- **Wolves**: Reduces health, but the hunter can protect others in society mode.

## How to Run
1. Compile the Java file:
   ```bash
   javac SurvivalSimulation.java
   ```
2. Run the simulation in **Society Mode**:
   ```bash
   java SurvivalSimulation society
   ```
3. Run the simulation in **Anarchy Mode**:
   ```bash
   java SurvivalSimulation anarchy
   ```

## Example Output
```
Running in anarchy mode ...

Day 1: famine
------------------------------
doctor : 10 7 10
farmer : 10 7 10
carpenter : 10 7 10
hunter : 10 7 10

Day 2: famine
------------------------------
doctor : 10 4 10
farmer : 10 4 10
carpenter : 10 4 10
hunter : 10 4 10
...
```

## Customization
- Modify roles, skills, and disasters to explore different scenarios.
- Adjust initial attribute values or disaster probabilities.

## License
This project is licensed under the MIT License.

## Author
Developed by [Adam](https://www.adamdoing.tech) for [CS 230](https://my.boisestate.edu/course-search/courses/1249/ugrd/CS/121897).

## Note: 
Feel free to modify and extend this simulation to explore survival dynamics under different social structures!

import java.util.Random;
import java.util.Scanner;

public class SmartIrrigationSystem {
    private final Sensor moistureSensor; //added final
    private final Sensor weatherSensor; //added final
    private final IrrigationStrategy irrigationStrategy; //added finalF
    private static final Random random = new Random();
    private String frequency;
    private int duration;



    public SmartIrrigationSystem(Sensor moistureSensor, Sensor weatherSensor, IrrigationStrategy irrigationStrategy, LivestockHealthMonitor livestockHealthMonitor, LivestockProductionManager livestockProductionManager) {
        this.moistureSensor = moistureSensor;
        this.weatherSensor = weatherSensor;
        this.irrigationStrategy = irrigationStrategy;
    }

    public void collectSensorDataAndIrrigate() {
        double moistureLevel = moistureSensor.readMoistureLevel();
        String weatherCondition = moistureSensor.readWeatherCondition();
        double cropWaterRequirement = 100.0;
        //Determine irrigation amount using the selected strategy
        double irrigationAmount = irrigationStrategy.determineIrrigationAmount(moistureLevel, weatherCondition, cropWaterRequirement);

        //print sensor data
        System.out.println("Moisture Level: " + moistureLevel);
        System.out.println("Weather Condition: " + weatherCondition);
        System.out.println("Irrigation Amount: " + irrigationAmount);

        //Invoke irrigation based on the determined amount
        if (irrigationAmount > 0) {
            irrigationStrategy.scheduleIrrigation();
        } else {
            System.out.println("No irrigation needed.");
        }
    }


    private void applyFertilizer() {
        System.out.println("Fertilizer applied to the crops.");
    }

    private void adjustAdvancedIrrigationSchedule() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the new duration (minutes per session): ");
        int newDuration = input.nextInt();
        System.out.println("Enter the new frequency: ");
        int newFrequency = input.nextInt();
        AdvancedIrrigationStrategy advancedIrrigationStrategy = new AdvancedIrrigationStrategy(moistureSensor, weatherSensor, 100.0, "Clay", "Hilly", newDuration, newFrequency);
        advancedIrrigationStrategy.adjustIrrigationSchedule();
    }

    private void considerSoilTypeAndTopography() {
        AdvancedIrrigationStrategy advancedIrrigationStrategy = new AdvancedIrrigationStrategy(moistureSensor, weatherSensor, 100.0, "Clay", "Hilly", 30, 3);
        advancedIrrigationStrategy.considerSoilTypeAndTopography();
    }

    private void optimizeResourceUsage(String formattedMoistureLevel) {
        System.out.println("Optimizing water and energy usage...");
        String weatherCondition = weatherSensor.readWeatherCondition();

        IrrigationSystem irrigationSystem = new IrrigationSystem();
        //irrigationSystem.optimizeWaterUsage(moistureLevel, weatherCondition);
        String moistureStatus;
        if (Double.parseDouble(formattedMoistureLevel) > 60) {
            moistureStatus = "Consider optimize your water and energy to save resource usage.";
        } else if (Double.parseDouble(formattedMoistureLevel) <= 40) {
            moistureStatus = "Consider more irrigation for your soil.";
        } else {
            moistureStatus = "No actions needed.";
        }
        irrigationSystem.optimizeEnergyUsage(weatherCondition);
        irrigationSystem.optimizeWaterUsage(Double.parseDouble(formattedMoistureLevel), weatherCondition);
        System.out.println(moistureStatus);
        System.out.println("Water and energy usage optimized successfully.");
    }

    private void monitorCropHealth() {
        if (isCropHealthCheckNeeded()) {
            String crop = getRandomCrop();
            String action = getActionForCrop(crop);
            if (!action.isEmpty()) {
                System.out.println("Action for " + crop + ": " + action);
            } else {
                System.out.println("No action needed.");
            }
        } else {
            System.out.println("Crop health is good. No issues detected.");
        }
    }

    private void manageWaste() {
        Scanner scanner = new Scanner(System.in);
        WasteManagement wasteManagement = new WasteManagement();

        System.out.println("==== Waste Management ====");
        System.out.println("1. Manage Waste");
        System.out.println("2. Recycle Materials");
        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Enter the type of waste: ");
                scanner.nextLine(); // Consume the newline character
                String wasteType = scanner.nextLine();
                wasteManagement.manageWaste(wasteType);
                break;
            case 2:
                System.out.println("Enter the type of material to recycle: ");
                scanner.nextLine(); // Consume the newline character
                String materialType = scanner.nextLine();
                wasteManagement.recycleMaterials(materialType);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

   private int getRandomNumber(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }


    private String getRandomCrop() {
        String[] crops = {"Corn", "Tomatoes", "Wheat"};
        return crops[getRandomNumber(0, 2)];
    }

    private boolean isCropHealthCheckNeeded() {
        return new Random().nextBoolean();
    }

    private String getActionForCrop(String crop) {
        return switch (crop) {
            case "Corn" -> "Apply targeted pesticide to address pests or diseases.";
            case "Tomatoes" -> "Increase watering frequency and apply balanced fertilizer.";
            case "Wheat" -> "Check for signs of fungal infection and apply appropriate fungicides. Adjust irrigation settings to ensure adequate moisture without water logging.";
            default -> "No action needed.";
        };
    }

    public boolean detectPestPresence() {
        //assume we randomly detect pests
        double probability = Math.random();
        return probability < 0.5; //assuming a 50% chance of detecting pests
    }

    public double measureNutrientLevel() {
        //generate random nutrient level between 0 and 100
        return Math.random() * 100;
    }

    private void viewSensorData() {
        double moistureLevel;
        String weatherCondition = weatherSensor.readWeatherCondition();
        String formattedMoistureLevel;

        if (weatherCondition != null && !weatherCondition.isEmpty()) {
            moistureLevel = moistureSensor.readMoistureLevel(weatherCondition);
        } else {
            moistureLevel = moistureSensor.readMoistureLevel();
        }
        formattedMoistureLevel = String.format("%.2f", moistureLevel);
        System.out.println("Sensor Data:");
        System.out.println("Moisture Level: " + formattedMoistureLevel + "%");
        System.out.println("Weather Condition: " + weatherCondition);
    }

    private String getRandomFrequency() {
        String[] frequencies = {"Once a day", "Twice a day", "Three times a day"};
        return frequencies[random.nextInt(frequencies.length)];
    }

    private int mapFrequencyToNumber(String frequency) {
        return switch (frequency) {
            case "Twice a day" -> 2;
            case "Three times a day" -> 3;
            default -> 1;  // "Once a day" or any unrecognized value
        };
    }

    private int getRandomDuration() {
        int[] durations = {20, 15, 30};
        return durations[random.nextInt(durations.length)];
    }

    private void adjustIrrigationSettings() {
        String frequencyString = getRandomFrequency(); //get frequency as a string
        this.frequency = frequencyString;
        int frequencyNumber = mapFrequencyToNumber(frequencyString);
        duration = getRandomDuration();

        //Determine irrigation amount based on current sensor readings and crop requirements
        String formattedMoistureLevel = String.format("%.2f", moistureSensor.readMoistureLevel());
        System.out.println("Irrigation Scheduling Preferences Updated: ");
        System.out.println("Frequency: " + frequencyNumber);
        System.out.println("Duration: " + duration + " minutes per session.");
        System.out.println("Moisture Level: " + formattedMoistureLevel + "%");
        System.out.println("Irrigation Amount: " + duration * frequencyNumber + " gallons");

        double moistureLevel = Double.parseDouble(formattedMoistureLevel);
        //Based on the determined irrigation amount, update irrigation tasks
        if (moistureLevel <= 40) {
            System.out.println("Moisture level is low. More irrigation please...");
        } else if (moistureLevel > 60){
            System.out.println("Soil moisture level is sufficient.");
        } else {
            System.out.println("Moisture level is moderate. No immediate action required.");
        }

        optimizeResourceUsage(formattedMoistureLevel);

    }

    private void adjustFertilizationSettings() {

        double moistureLevel = moistureSensor.readMoistureLevel();
        String weatherCondition = weatherSensor.readWeatherCondition();
        String formattedMoistureLevel = String.format("%.2f", moistureLevel);
        System.out.println("Sensor Readings: - Soil Moisture Level: " + formattedMoistureLevel + "% - Weather Condition: " + weatherCondition);

    }

    private void generateResourceUsageReport(String frequency, int duration) {
        int multiplier = switch (frequency) {
            case "Twice a day" -> 2;
            case "Three times a day" -> 3;
            default -> 1; //Once a day

        };
        double totalWaterUsagePerDay = duration * multiplier;
        double totalHoursPerDay = (duration * multiplier) / 60.0; //convert duration from minutes to hours for energy calculation
        double powerConsumption = 1.0;  //kW
        double totalEnergyUsagePerDay = powerConsumption * totalHoursPerDay; //energy consumption
        String formattedTotalEnergyUsagePerDay = String.format("%.2f", totalEnergyUsagePerDay);

        // Print the resource usage report
        System.out.println("Resource Usage Report:");
        System.out.println("Water Usage: " + totalWaterUsagePerDay + " gallons per day");
        System.out.println("Energy Usage: " + formattedTotalEnergyUsagePerDay + " kWh per day");

    }


    private void monitorLivestockHealth() {
        Scanner scanner = new Scanner(System.in);
        Livestock livestock; // it was Livestock livestock = null;
        System.out.println("Select the type of livestock:");
        System.out.println("1. Cow");
        System.out.println("2. Cattle");
        System.out.println("3. Poultry");
        System.out.println("4. Sheep");
        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                livestock = new Cow("Daisy", "Normal", "Balanced diet");
                livestock.setWeight(500); //Weight for cow
                livestock.setTemperature(38.5); //Body temperature for cow
                livestock.setHeartRate(80); //Set the heart rate
                livestock.setRespiratoryRate(20);
                break;
            case 2:
                livestock = new Cattle("Bessie", "Healthy", "Grass diet", 10.5);
                livestock.setWeight(700); //Example weight for cattle
                livestock.setTemperature(38.5); //Typical body temperature for cattle
                livestock.setHeartRate(65); //Typical heart rate for cattle
                livestock.setRespiratoryRate(30); //Typical respiratory rate for cattle
                break;
            case 3:
                livestock = new Poultry("Healthy", "Grains", 20);
                break;
            case 4:
                livestock = new Sheep("Dolly", "Healthy", "Grass diet", 5);
                livestock.setWeight(50); //Example weight for sheep
                livestock.setTemperature(39.0); //Typical body temperature for sheep
                livestock.setHeartRate(70); //Typical heart rate for sheep
                livestock.setRespiratoryRate(20); //Typical respiratory rate for sheep
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        LivestockHealthMonitor monitor = new LivestockHealthMonitor();
        System.out.println("1. View Health Indicators");
        System.out.println("2. Track Health Trends");
        System.out.println("3. Feed Livestock");
        System.out.println("4. Administer Medication");
        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                monitor.viewHealthIndicators(livestock);
                break;
            case 2:
                monitor.trackHealthTrends(livestock);
                break;
            case 3:
                livestock.feed(5, 2); // Sample feeding values
                break;
            case 4:
                livestock.administerMedication("Vaccination", 10, "Intramuscular");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private String detectCropNutrients() {
        String[] cropNutrients = {"healthy", "unhealthy", "mildly ill", "sickly"};
        return cropNutrients[random.nextInt(cropNutrients.length)];
    }

    private String detectCropGrowth() {
        String[] cropGrowth = {"fully grown", "middle stage growth", "hasn't grown yet", "just planted"};
        return cropGrowth[random.nextInt(cropGrowth.length)];
    }

    private String detectCropDisease(String nutrientStatus) {
        Random rand = new Random();
        double diseaseProbability = switch (nutrientStatus) {
            case "healthy" -> 0.1; //10% chance of disease if healthy
            case "unhealthy", "mildly ill" -> 0.5; //50% chance if not optimal
            case "sickly" -> 0.8; //80% chance if very poor
            default -> 0.3;
        };
        return rand.nextDouble() < diseaseProbability ? "diseases detected" : "diseases not detected";
    }

    private void cropFertilizationDecision(String nutrientStatus) {
        switch (nutrientStatus) {
            case "healthy":
                System.out.println("Fertilization is not needed due to healthy nutrients.");
                break;
            case "unhealthy":
                System.out.println("Fertilization is needed at convenience.");
                break;
            case "mildly ill":
                System.out.println("Fertilization is needed NOW.");
                break;
            case "sickly":
                System.out.println("Fertilization CANNOT wait.");
                break;
            default:
                System.out.println("Check crop status.");
                break;
        }
    }

    private void viewCropInformation() {
        String[] cropNames = {"Corn", "Tomatoes", "Wheat"};
        int randomIndex = random.nextInt(cropNames.length); // Get a random index for one crop

        String cropName = cropNames[randomIndex];
        String growth = detectCropGrowth();
        String nutrients = detectCropNutrients();
        String disease = detectCropDisease(nutrients);

        System.out.println("Crop Information:");
        System.out.println("Name: " + cropName);
        System.out.println("Stage: " + growth);
        System.out.println("Nutrient: " + nutrients);
        System.out.println("Status: " + disease);
        cropFertilizationDecision(nutrients);
        System.out.println();
    }





    private void manageCarbonFootprint() {
        Scanner scanner = new Scanner(System.in);
        CarbonFootPrintManager footPrintManager = new CarbonFootPrintManager();
        System.out.println("=== Carbon Footprint Reduction ===");
        System.out.println("1. Track Emissions");
        System.out.println("2. Reduce Emissions");
        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                footPrintManager.trackEmissions();
                break;
            case 2:
                footPrintManager.reduceEmissions();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void manageFarmDecisions() {
        Scanner scanner = new Scanner(System.in);
        FarmDecisionMaker decisionMaker = new FarmDecisionMaker();
        System.out.println("=== Farm Decision Making ===");
        System.out.println("1. Make Irrigation Decision");
        System.out.println("2. Make Fertilization Decision");
        System.out.println("3. Make Pest Control Decision");
        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                decisionMaker.makeIrrigationDecision(0.7, "Sunny");
                break;
            case 2:
                decisionMaker.makeFertilizationDecision("Tomatoes");
                break;
            case 3:
                decisionMaker.makePestControlDecision("Corn");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private String getRandomHealthStatus() {
        String[] healthStatuses = {"Good", "Fair", "Needs Maintenance"};
        return healthStatuses[random.nextInt(healthStatuses.length)];
    }

    private void displaySystemStatus(){
        String healthStatus = getRandomHealthStatus();
        String softwareVersion = "v1.2.0"; //Fixed software version, but can also be updated

        System.out.println("System Status: - Overall Health: " + healthStatus + " - Software Version: " + softwareVersion);
        if (healthStatus.equals("Needs Maintenance")) {
            System.out.println("Please check the system for possible issues.");
        }
    }

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        //boolean running = true;

        while (true) {
            System.out.println("==== Smart Irrigation System Menu ====");
            System.out.println("1. View Sensor Data");
            System.out.println("2. Adjust Irrigation Settings");
            System.out.println("3. Initiate Sensor Readings");
            System.out.println("4. Resource Usage Report");
            System.out.println("5. Monitor Livestock Health");
            System.out.println("6. Manage Crops");
            System.out.println("7. Monitor Crop Health");
            System.out.println("8. Apply Fertilizer");
            System.out.println("9. Waste Management");
            System.out.println("10. Carbon Footprint Reduction");
            System.out.println("11. Farm Decision Making");
            System.out.println("12. Irrigation Schedule");
            System.out.println("13. Soil Information");
            System.out.println("14. Exit");
            System.out.println("Enter your choice: ");
            System.out.println();

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewSensorData();
                    System.out.println();
                    break;
                case 2:
                    adjustIrrigationSettings();
                    System.out.println();
                    break;
                case 3:
                    adjustFertilizationSettings();
                    System.out.println();
                    break;
                case 4:
                    adjustIrrigationSettings();
                    System.out.println();
                    generateResourceUsageReport(frequency, duration);
                    System.out.println();
                    break;
                case 5:
                    monitorLivestockHealth();
                    System.out.println();
                    break;
                case 6:
                    viewCropInformation();
                    System.out.println();
                    break;
                case 7:
                    monitorCropHealth();
                    System.out.println();
                    break;
                case 8:
                    applyFertilizer();
                    System.out.println();
                    break;
                case 9:
                    manageWaste();
                    System.out.println();
                    break;
                case 10:
                    manageCarbonFootprint();
                    System.out.println();
                    break;
                case 11:
                    manageFarmDecisions();
                    System.out.println();
                    break;
                case 12:
                    adjustAdvancedIrrigationSchedule();
                    System.out.println();
                    break;
                case 13:
                    considerSoilTypeAndTopography();
                    System.out.println();
                    break;
                case 14:
                    displaySystemStatus();
                    System.out.println("Thank you for using the Smart Irrigation System!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println();
            }
        }
    }

    private static SmartIrrigationSystem initializeSystem() {
        Sensor moistureSensor = new SoilMoistureSensor();
        Sensor weatherSensor = new WeatherSensor();
        int defaultDuration = 30;
        int defaultFrequency = 1;

        IrrigationStrategy irrigationStrategy = new AdvancedIrrigationStrategy(
                moistureSensor, weatherSensor, 100.0, "Clay", "Hilly", defaultDuration, defaultFrequency);

        //Create livestock health
        LivestockHealthMonitor livestockHealthMonitor = new LivestockHealthMonitor();
        LivestockProductionManager livestockProductionManager = new LivestockProductionManager();
        return new SmartIrrigationSystem(moistureSensor, weatherSensor, irrigationStrategy, livestockHealthMonitor, livestockProductionManager);

    }

    public static void main(String[] args) {

        //Create the smart farming system
        SmartIrrigationSystem irrigationSystem = initializeSystem();

        //Start the menu
        irrigationSystem.startMenu();

        //Collect sensor data and irrigate
        irrigationSystem.collectSensorDataAndIrrigate();

        //generate resource usage report
        irrigationSystem.generateResourceUsageReport(irrigationSystem.getRandomFrequency(), irrigationSystem.getRandomDuration());
        irrigationSystem.applyFertilizer();

        //Detect pest presence and measure nutrient levels
        boolean pestDetected = irrigationSystem.detectPestPresence();
        double nutrientLevel = irrigationSystem.measureNutrientLevel();

        //print results
        System.out.println("Pest detected: " + pestDetected);
        System.out.println("Nutrient level in soil: " + nutrientLevel);
    }
}

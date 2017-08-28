package com.appl_maint_mngt.appliance.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.appl_maint_mngt.appliance.models.constants.ApplianceType;
import com.appl_maint_mngt.appliance.models.constants.ClimateClass;
import com.appl_maint_mngt.appliance.models.constants.ConstructionType;
import com.appl_maint_mngt.appliance.models.constants.ControlType;
import com.appl_maint_mngt.appliance.models.constants.CoolingAgent;
import com.appl_maint_mngt.appliance.models.constants.DoorType;
import com.appl_maint_mngt.appliance.models.constants.DryingPerformanceClass;
import com.appl_maint_mngt.appliance.models.constants.EnergyRating;
import com.appl_maint_mngt.appliance.models.constants.FitType;
import com.appl_maint_mngt.appliance.models.constants.GrillType;
import com.appl_maint_mngt.appliance.models.constants.LoadingType;
import com.appl_maint_mngt.appliance.models.constants.PowerSource;
import com.appl_maint_mngt.appliance.models.constants.WashingPerformanceClass;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document(collection="Appliance")
public class Appliance {

	@Id
	private String id;

	private String productNo;
	private String name;
	private String brand;
	private ApplianceType type;
	private List<String> features;

	// mm
	private double width;
	private double height;
	private double depth;

	// kg
	private double weight;
	
	private ApplianceImage applianceImage;
	
/*============================================================================ */
	
	private int placeSettings;
	private int presetCycles;

	private boolean delayStart;
	private boolean antiFloodProtection;

	// db
	private int noiseLevel;

	private ControlType controlType;
	
	private boolean interiorLight;
	private boolean lcd;
	private boolean led;

	// litres
	private int annualWaterConsumption;

	// kwh
	private int annualEnergyConsumption;

	// Hz
	private int frequency;

	// V
	private int voltageMin;
	private int voltageMax;

	private DryingPerformanceClass dryingPerformanceClass;
	private WashingPerformanceClass washingPerformanceClass;
	private EnergyRating energyRating;

	private FitType fitType;

	private int fridgePercentage;
	private int freezerPercentage;

	private boolean waterDispenser;
	private boolean autoDefrost;

	private ClimateClass climateClass;

	private int noOfCompressors;

	// hours
	private int powerFailureSafeStorageTime;

	// litres
	private int fridgeCapacity;

	private int fridgeShelves;

	private boolean fastFreeze;

	// kg
	private int freezingCapacity;

	// litre
	private int freezerCapacity;

	private int freezerShelves;
	private boolean frostFree;

	private DoorType doorType;

	private int noOfDoors;

	private List<String> approvedCertifications;

	private boolean plumbed;
	
	private CoolingAgent coolingAgent;

	//litres
	private double capacity;
	
	private boolean defrostFunction;

	private String cavitySurfaceMaterial;
	
	// W
	private int power;
	
	private int powerLevelNo;
	private int grillPower;
	
	//mm
	private double turntableSize;
	
	private int autoFunctions;

	private int noOfOvens;

	private boolean singleOven;
	private boolean doubleOven;

	private ConstructionType constructionType;
	private String fuelType;

	private List<String> cleaning;

	private boolean interiorLights;

	private boolean autoShutOff;

	// A
	private double electricalConnection;

	private PowerSource powerSource;

	private GrillType grillType;

	private int noOfFunctions;

	private boolean vented;
	private boolean condensed;

	// kg
	private int dryerCapacity;

	// db
	private int spinNoiseLevel;

	private boolean inbuiltHome;

	private int numberOfProgrammes;

	private LoadingType loadingType;

	// db
	private int washNoiseLevel;

	private boolean quickWash;

	private boolean inBuiltHeater;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonInclude(Include.NON_NULL)
	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	@JsonInclude(Include.NON_NULL)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonInclude(Include.NON_NULL)
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@JsonInclude(Include.NON_NULL)
	public ApplianceType getType() {
		return type;
	}

	public void setType(ApplianceType type) {
		this.type = type;
	}

	@JsonInclude(Include.NON_NULL)
	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public double getDepth() {
		return depth;
	}

	public void setDepth(double depth) {
		this.depth = depth;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@JsonInclude(Include.NON_NULL)
	public ApplianceImage getApplianceImage() {
		return applianceImage;
	}

	public void setApplianceImage(ApplianceImage image) {
		this.applianceImage = image;
	}

	
/*============================================================================ */
	
	
	@JsonInclude(Include.NON_DEFAULT)
	public int getPlaceSettings() {
		return placeSettings;
	}

	public void setPlaceSettings(int placeSettings) {
		this.placeSettings = placeSettings;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getPresetCycles() {
		return presetCycles;
	}

	public void setPresetCycles(int presetCycles) {
		this.presetCycles = presetCycles;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public boolean getDelayStart() {
		return delayStart;
	}

	public void setDelayStart(boolean delayStart) {
		this.delayStart = delayStart;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public boolean getAntiFloodProtection() {
		return antiFloodProtection;
	}

	public void setAntiFloodProtection(boolean antiFloodProtection) {
		this.antiFloodProtection = antiFloodProtection;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getNoiseLevel() {
		return noiseLevel;
	}

	public void setNoiseLevel(int noiseLevel) {
		this.noiseLevel = noiseLevel;
	}

	@JsonInclude(Include.NON_NULL)
	public ControlType getControlType() {
		return controlType;
	}

	public void setControlType(ControlType controlType) {
		this.controlType = controlType;
	}
	
	@JsonInclude(Include.NON_DEFAULT)
	public boolean getInteriorLight() {
		return interiorLight;
	}

	public void setInteriorLight(boolean interiorLight) {
		this.interiorLight = interiorLight;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public boolean getLcd() {
		return lcd;
	}

	public void setLcd(boolean lcd) {
		this.lcd = lcd;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public boolean getLed() {
		return led;
	}

	public void setLed(boolean led) {
		this.led = led;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getAnnualWaterConsumption() {
		return annualWaterConsumption;
	}

	public void setAnnualWaterConsumption(int annualWaterConsumption) {
		this.annualWaterConsumption = annualWaterConsumption;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getAnnualEnergyConsumption() {
		return annualEnergyConsumption;
	}

	public void setAnnualEnergyConsumption(int annualEnergyConsumption) {
		this.annualEnergyConsumption = annualEnergyConsumption;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getVoltageMin() {
		return voltageMin;
	}

	public void setVoltageMin(int voltageMin) {
		this.voltageMin = voltageMin;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getVoltageMax() {
		return voltageMax;
	}

	public void setVoltageMax(int voltageMax) {
		this.voltageMax = voltageMax;
	}

	@JsonInclude(Include.NON_NULL)
	public DryingPerformanceClass getDryingPerformanceClass() {
		return dryingPerformanceClass;
	}

	public void setDryingPerformanceClass(DryingPerformanceClass dryingPerformanceClass) {
		this.dryingPerformanceClass = dryingPerformanceClass;
	}
	
	@JsonInclude(Include.NON_NULL)
	public WashingPerformanceClass getWashingPerformanceClass() {
		return washingPerformanceClass;
	}

	public void setWashingPerformanceClass(WashingPerformanceClass washingPerformanceClass) {
		this.washingPerformanceClass = washingPerformanceClass;
	}
	
	@JsonInclude(Include.NON_NULL)
	public EnergyRating getEnergyRating() {
		return energyRating;
	}

	public void setEnergyRating(EnergyRating energyRating) {
		this.energyRating = energyRating;
	}

	@JsonInclude(Include.NON_NULL)
	public FitType getFitType() {
		return fitType;
	}

	public void setFitType(FitType fitType) {
		this.fitType = fitType;
	}
	
	@JsonInclude(Include.NON_DEFAULT)
	public int getFridgePercentage() {
		return fridgePercentage;
	}

	public void setFridgePercentage(int fridgePercentage) {
		this.fridgePercentage = fridgePercentage;
	}
	
	@JsonInclude(Include.NON_DEFAULT)
	public int getFreezerPercentage() {
		return freezerPercentage;
	}

	public void setFreezerPercentage(int freezerPercentage) {
		this.freezerPercentage = freezerPercentage;
	}
	
	@JsonInclude(Include.NON_DEFAULT)
	public boolean getWaterDispenser() {
		return waterDispenser;
	}

	public void setWaterDispenser(boolean waterDispenser) {
		this.waterDispenser = waterDispenser;
	}

	public boolean getAutoDefrost() {
		return autoDefrost;
	}

	public void setAutoDefrost(boolean autoDefrost) {
		this.autoDefrost = autoDefrost;
	}

	@JsonInclude(Include.NON_NULL)
	public ClimateClass getClimateClass() {
		return climateClass;
	}

	public void setClimateClass(ClimateClass climateClass) {
		this.climateClass = climateClass;
	}
	
	@JsonInclude(Include.NON_DEFAULT)
	public int getNoOfCompressors() {
		return noOfCompressors;
	}

	public void setNoOfCompressors(int noOfCompressors) {
		this.noOfCompressors = noOfCompressors;
	}
	
	@JsonInclude(Include.NON_DEFAULT)
	public int getPowerFailureSafeStorageTime() {
		return powerFailureSafeStorageTime;
	}

	public void setPowerFailureSafeStorageTime(int powerFailureSafeStorageTime) {
		this.powerFailureSafeStorageTime = powerFailureSafeStorageTime;
	}
	
	@JsonInclude(Include.NON_DEFAULT)
	public int getFridgeCapacity() {
		return fridgeCapacity;
	}

	public void setFridgeCapacity(int fridgeCapacity) {
		this.fridgeCapacity = fridgeCapacity;
	}
	
	@JsonInclude(Include.NON_DEFAULT)
	public int getFridgeShelves() {
		return fridgeShelves;
	}

	public void setFridgeShelves(int fridgeShelves) {
		this.fridgeShelves = fridgeShelves;
	}
	
	@JsonInclude(Include.NON_DEFAULT)
	public boolean getFastFreeze() {
		return fastFreeze;
	}

	public void setFastFreeze(boolean fastFreeze) {
		this.fastFreeze = fastFreeze;
	}
	
	@JsonInclude(Include.NON_DEFAULT)
	public int getFreezingCapacity() {
		return freezingCapacity;
	}

	public void setFreezingCapacity(int freezingCapacity) {
		this.freezingCapacity = freezingCapacity;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getFreezerCapacity() {
		return freezerCapacity;
	}

	public void setFreezerCapacity(int freezerCapacity) {
		this.freezerCapacity = freezerCapacity;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getFreezerShelves() {
		return freezerShelves;
	}

	public void setFreezerShelves(int freezerShelves) {
		this.freezerShelves = freezerShelves;
	}
	
	@JsonInclude(Include.NON_DEFAULT)
	public boolean isFrostFree() {
		return frostFree;
	}

	public void setFrostFree(boolean frostFree) {
		this.frostFree = frostFree;
	}

	@JsonInclude(Include.NON_NULL)
	public DoorType getDoorType() {
		return doorType;
	}

	public void setDoorType(DoorType doorType) {
		this.doorType = doorType;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getNoOfDoors() {
		return noOfDoors;
	}

	public void setNoOfDoors(int noOfDoors) {
		this.noOfDoors = noOfDoors;
	}

	@JsonInclude(Include.NON_NULL)
	public List<String> getApprovedCertifications() {
		return approvedCertifications;
	}

	public void setApprovedCertifications(List<String> approvedCertifications) {
		this.approvedCertifications = approvedCertifications;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public boolean getPlumbed() {
		return plumbed;
	}

	public void setPlumbed(boolean plumbed) {
		this.plumbed = plumbed;
	}

	@JsonInclude(Include.NON_NULL)
	public CoolingAgent getCoolingAgent() {
		return coolingAgent;
	}

	public void setCoolingAgent(CoolingAgent coolingAgent) {
		this.coolingAgent = coolingAgent;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public boolean getDefrostFunction() {
		return defrostFunction;
	}

	public void setDefrostFunction(boolean defrostFunction) {
		this.defrostFunction = defrostFunction;
	}

	@JsonInclude(Include.NON_NULL)
	public String getCavitySurfaceMaterial() {
		return cavitySurfaceMaterial;
	}

	public void setCavitySurfaceMaterial(String cavitySurfaceMaterial) {
		this.cavitySurfaceMaterial = cavitySurfaceMaterial;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getPowerLevelNo() {
		return powerLevelNo;
	}

	public void setPowerLevelNo(int powerLevelNo) {
		this.powerLevelNo = powerLevelNo;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getGrillPower() {
		return grillPower;
	}

	public void setGrillPower(int grillPower) {
		this.grillPower = grillPower;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public double getTurntableSize() {
		return turntableSize;
	}

	public void setTurntableSize(double turntableSize) {
		this.turntableSize = turntableSize;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getAutoFunctions() {
		return autoFunctions;
	}

	public void setAutoFunctions(int autoFunctions) {
		this.autoFunctions = autoFunctions;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getNoOfOvens() {
		return noOfOvens;
	}

	public void setNoOfOvens(int noOfOvens) {
		this.noOfOvens = noOfOvens;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public boolean getSingleOven() {
		return singleOven;
	}

	public void setSingleOven(boolean singleOven) {
		this.singleOven = singleOven;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public boolean getDoubleOven() {
		return doubleOven;
	}

	public void setDoubleOven(boolean doubleOven) {
		this.doubleOven = doubleOven;
	}

	@JsonInclude(Include.NON_NULL)
	public ConstructionType getConstructionType() {
		return constructionType;
	}

	public void setConstructionType(ConstructionType constructionType) {
		this.constructionType = constructionType;
	}

	@JsonInclude(Include.NON_NULL)
	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	@JsonInclude(Include.NON_NULL)
	public List<String> getCleaning() {
		return cleaning;
	}

	public void setCleaning(List<String> cleaning) {
		this.cleaning = cleaning;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public boolean getInteriorLights() {
		return interiorLights;
	}

	public void setInteriorLights(boolean interiorLights) {
		this.interiorLights = interiorLights;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public boolean getAutoShutOff() {
		return autoShutOff;
	}

	public void setAutoShutOff(boolean autoShutOff) {
		this.autoShutOff = autoShutOff;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public double getElectricalConnection() {
		return electricalConnection;
	}

	public void setElectricalConnection(double electricalConnection) {
		this.electricalConnection = electricalConnection;
	}

	@JsonInclude(Include.NON_NULL)
	public PowerSource getPowerSource() {
		return powerSource;
	}

	public void setPowerSource(PowerSource powerSource) {
		this.powerSource = powerSource;
	}

	@JsonInclude(Include.NON_NULL)
	public GrillType getGrillType() {
		return grillType;
	}

	public void setGrillType(GrillType grillType) {
		this.grillType = grillType;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getNoOfFunctions() {
		return noOfFunctions;
	}

	public void setNoOfFunctions(int noOfFunctions) {
		this.noOfFunctions = noOfFunctions;
	}
	@JsonInclude(Include.NON_DEFAULT)
	public boolean getVented() {
		return vented;
	}

	public void setVented(boolean vented) {
		this.vented = vented;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public boolean getCondensed() {
		return condensed;
	}

	public void setCondensed(boolean condensed) {
		this.condensed = condensed;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getDryerCapacity() {
		return dryerCapacity;
	}

	public void setDryerCapacity(int dryerCapacity) {
		this.dryerCapacity = dryerCapacity;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getSpinNoiseLevel() {
		return spinNoiseLevel;
	}

	public void setSpinNoiseLevel(int spinNoiseLevel) {
		this.spinNoiseLevel = spinNoiseLevel;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public boolean getInbuiltHome() {
		return inbuiltHome;
	}

	public void setInbuiltHome(boolean inbuiltHome) {
		this.inbuiltHome = inbuiltHome;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getNumberOfProgrammes() {
		return numberOfProgrammes;
	}

	public void setNumberOfProgrammes(int numberOfProgrammes) {
		this.numberOfProgrammes = numberOfProgrammes;
	}

	@JsonInclude(Include.NON_NULL)
	public LoadingType getLoadingType() {
		return loadingType;
	}

	public void setLoadingType(LoadingType loadingType) {
		this.loadingType = loadingType;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public int getWashNoiseLevel() {
		return washNoiseLevel;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public void setWashNoiseLevel(int washNoiseLevel) {
		this.washNoiseLevel = washNoiseLevel;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public boolean getQuickWash() {
		return quickWash;
	}

	public void setQuickWash(boolean quickWash) {
		this.quickWash = quickWash;
	}

	@JsonInclude(Include.NON_DEFAULT)
	public boolean getInBuiltHeater() {
		return inBuiltHeater;
	}

	public void setInBuiltHeater(boolean inBuiltHeater) {
		this.inBuiltHeater = inBuiltHeater;
	}

}

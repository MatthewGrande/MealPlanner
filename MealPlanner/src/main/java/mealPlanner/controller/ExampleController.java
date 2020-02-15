/*
 * package mealPlanner.controller;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * //import java.awt.List;
 * 
 * import org.modelmapper.ModelMapper; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import ca.mcgill.ecse321.TreePLE.dto.MunicipalityDto; import
 * ca.mcgill.ecse321.TreePLE.dto.StatusDto; import
 * ca.mcgill.ecse321.TreePLE.dto.TreeDto; import
 * ca.mcgill.ecse321.TreePLE.model.Municipality; import
 * ca.mcgill.ecse321.TreePLE.model.Municipality.MunicipalityName; import
 * ca.mcgill.ecse321.TreePLE.model.Person; import
 * ca.mcgill.ecse321.TreePLE.model.Scientist; import
 * ca.mcgill.ecse321.TreePLE.model.Status; import
 * ca.mcgill.ecse321.TreePLE.model.Status.TreeState; import
 * ca.mcgill.ecse321.TreePLE.model.Tree; import
 * ca.mcgill.ecse321.TreePLE.model.Tree.LandType; import
 * ca.mcgill.ecse321.TreePLE.model.Tree.TreeSpecies; import
 * ca.mcgill.ecse321.TreePLE.service.InvalidInputException; import
 * ca.mcgill.ecse321.TreePLE.service.TreePLETreeService;
 * 
 * @RestController public class MealPlannerRestController {
 * 
 * @Autowired private TreePLETreeService service;
 * 
 * @Autowired private ModelMapper modelMapper;
 * 
 * @RequestMapping("/") public String index() { return
 * "TreePLE application root. Web-based frontend is a TODO. Use the REST API to manage events and participants.\n"
 * ;
 * 
 * } //conversion tree method private TreeDto convertToDto(Tree t) { //mapper
 * service TreeDto treeDto = modelMapper.map(t, TreeDto.class); //
 * treeDto.setMunicipality(createMunicipalityDtoForTree(t)); return treeDto; }
 * 
 * 
 * //conversion municipality method private MunicipalityDto
 * convertToDto(Municipality m) {
 * 
 * return modelMapper.map(m, MunicipalityDto.class); }
 * 
 * //conversion status method private StatusDto convertToDto(Status s) {
 * StatusDto sDto = modelMapper.map(s, StatusDto.class);
 * 
 * return sDto; }
 * 
 * private MunicipalityDto createMunicipalityDtoForTree(Tree t){ Municipality m
 * = service.getMunicipalityForTree(t); return convertToDto(m); }
 * 
 * 
 * 
 * 
 * 
 * @GetMapping(value = { "/municipalities/", "/municipalities"})
 * 
 * public List<String> findMunicipalities() { List<String> names = new
 * ArrayList<String>(); for (MunicipalityName mun: MunicipalityName.values()) {
 * 
 * names.add(mun.toString()); } return names; }
 * 
 * 
 * 
 * @GetMapping(value = { "/landtypes/", "/landtypes" }) public List<String>
 * findLandTypes() { List<String> type = new ArrayList<String>(); for (LandType
 * mun: LandType.values()) { type.add(mun.toString()); } return type; }
 * 
 * 
 * 
 * @GetMapping(value = { "/trees", "/trees/" }) public List<TreeDto>
 * findAllTrees() throws InvalidInputException { List<TreeDto> trees = new
 * ArrayList<TreeDto>();
 * 
 * for (Tree tree : service.listAllTrees()) { trees.add(convertToDto(tree)); }
 * return trees; }
 * 
 * @GetMapping(value = { "/states", "/states/" }) public List<String>
 * findAllStates() throws InvalidInputException { List<String> states = new
 * ArrayList<String>();
 * 
 * for (String s : service.listAllStates()) { states.add(s); } return states; }
 * 
 * @GetMapping(value = { "/species", "/species/" }) public List<String>
 * findAllSpecies() throws InvalidInputException { List<String> species = new
 * ArrayList<String>();
 * 
 * for (String s : service.listAllSpecies()) { species.add(s); } return species;
 * }
 * 
 * @GetMapping(value = { "/CalculateCurrentBI/"}) public double
 * calculateCurrentBI() { double BI;
 * BI=service.CalculateCurrentBioDiversityIndex();
 * 
 * return BI; }
 * 
 * @GetMapping(value = { "/CalculateCurrentCarbonSEQ/"}) public int
 * calculateCurrentCarbonSEQ() { int seq;
 * seq=service.CalculateCurrentCarbonSeqPerYear();
 * 
 * return seq; }
 * 
 * 
 * @GetMapping(value = { "/CurrentTotalCanopy/"}) public double
 * currentTotalCanopy() { double can;
 * can=service.CalculateCurrentBioDiversityIndex();
 * 
 * return can; }
 * 
 * @GetMapping(value= {"/trees/{species}"}) public List<TreeDto>
 * getTreesBySpecies (@PathVariable (name="species") TreeSpecies species) throws
 * InvalidInputException {
 * 
 * List<TreeDto> trees= new ArrayList<TreeDto>(); for(Tree tree:
 * (service.getTreeBySpecies(species))) { trees.add(convertToDto(tree)); }
 * return trees; }
 * 
 * 
 * 
 * @GetMapping(value= {"/trees/{status}"}) public List<TreeDto>
 * getTreesBySpecies (@PathVariable (name="status") TreeState state) throws
 * InvalidInputException {
 * 
 * List<TreeDto> trees= new ArrayList<TreeDto>(); for(Tree tree:
 * (service.getTreeByState(state))) { trees.add(convertToDto(tree)); } return
 * trees; }
 * 
 * @GetMapping(value= {"/trees/{MunicipalityName}"}) public List<TreeDto>
 * getTreesByMunicipality (@PathVariable (name="mun") MunicipalityName munName)
 * throws InvalidInputException {
 * 
 * List<TreeDto> trees= new ArrayList<TreeDto>(); for(Tree tree:
 * (service.getTreeByMunicipality(munName))) { trees.add(convertToDto(tree)); }
 * return trees; }
 * 
 * 
 * 
 * @PostMapping(value = { "/Login/{userName}/{userRole}"}) public void Login(
 * 
 * @PathVariable("userName") String userName,
 * 
 * @PathVariable ("userRole") String userRole) { Boolean ans=true; try { ans =
 * service.login(userName, userRole); } catch (InvalidInputException e) { //
 * TODO Auto-generated catch block e.printStackTrace(); }
 * 
 * 
 * }
 * 
 * @PostMapping(value = { "/PlantTree/{userName}"}) public TreeDto plantTree(
 * 
 * @PathVariable("userName") String userName,
 * 
 * @RequestParam (name="landType") LandType landtype,
 * 
 * @RequestParam (name="species") TreeSpecies species,
 * 
 * @RequestParam (name="height") double height,
 * 
 * @RequestParam (name="diameter") double diameter,
 * 
 * @RequestParam (name="longitude") double longitude,
 * 
 * @RequestParam (name="latitude") double latitude,
 * 
 * @RequestParam (name="municipality") MunicipalityName municipalityName
 * 
 * ) throws InvalidInputException {
 * 
 * ;
 * 
 * Tree tree= service.plantTree(landtype, species, height,diameter, longitude,
 * latitude, municipalityName,userName);
 * 
 * return convertToDto(tree); }
 * 
 * 
 * 
 * 
 * 
 * 
 * @GetMapping(value = { "/mun", "/mun/" }) public List<MunicipalityDto>
 * findAllMun() {
 * 
 * List<MunicipalityDto> mun = new ArrayList<MunicipalityDto>(); for
 * (Municipality municip : service.listAllMunicipalities()) {
 * mun.add(convertToDto(municip)); } return mun;
 * 
 * }
 * 
 * @GetMapping(value = { "/scientists/", "/scientists" }) public List<String>
 * findAllScientists() { List<String> scientistNames = new ArrayList<String>();
 * for(Person m: service.listAllUsers()) { if
 * (m.getRoles().getClass()==Scientist.class) { scientistNames.add(m.getName());
 * } } return scientistNames; }
 * 
 * 
 * //Delete / Cut Tree
 * 
 * @PostMapping(value = { "/cutDownTree/{latitude}/{longitude}/{userName}" })
 * 
 * 
 * public TreeDto cutDownTree(
 * 
 * @PathVariable("latitude") double latitude,
 * 
 * @PathVariable("longitude") double longitude,
 * 
 * @PathVariable("userName") String userName) throws InvalidInputException {
 * 
 * Tree t = service.getPlantedTreeByLocation(latitude,longitude);
 * 
 * service.cutDownTree(t,userName); return convertToDto(t); }
 * 
 * 
 * 
 * @PostMapping(value = { "/cutDownTree/{treeID}/{userName}" })
 * 
 * 
 * public TreeDto cutDownTreeWithID(
 * 
 * @PathVariable("treeID") int treeID,
 * 
 * @PathVariable("userName") String userName) throws InvalidInputException {
 * 
 * Tree t = service.getTreeByID(treeID);
 * 
 * service.cutDownTree(t,userName); return convertToDto(t); }
 * 
 * 
 * 
 * @PostMapping(value = { "/MarktoBeCutDown/{latitude}/{longitude}/{userName}"
 * })
 * 
 * 
 * public TreeDto MarkToBeCutDown(
 * 
 * @PathVariable("latitude") double latitude,
 * 
 * @PathVariable("longitude") double longitude,
 * 
 * @PathVariable("userName") String userName) throws InvalidInputException {
 * 
 * Tree t = service.getPlantedTreeByLocation(latitude,longitude);
 * 
 * service.MarkTreeToBeCutDown(t,userName); return convertToDto(t); }
 * 
 * @PostMapping(value = { "/MarktoBeCutDown/{treeID}/{userName}" })
 * 
 * 
 * public TreeDto MarkToBeCutDownWithID(
 * 
 * @PathVariable("treeID") int treeID,
 * 
 * @PathVariable("userName") String userName) throws InvalidInputException {
 * 
 * Tree t = service.getTreeByID(treeID);
 * 
 * service.MarkTreeToBeCutDown(t,userName); return convertToDto(t); }
 * 
 * 
 * @PostMapping(value = { "/MarkAsDiseased/{latitude}/{longitude}/{userName}" })
 * 
 * 
 * public TreeDto MarkAsDiseased(
 * 
 * @PathVariable("latitude") double latitude,
 * 
 * @PathVariable("longitude") double longitude,
 * 
 * @PathVariable("userName") String userName) throws InvalidInputException {
 * 
 * Tree t = service.getPlantedTreeByLocation(latitude,longitude);
 * 
 * service.MarkTreeAsDiseased(t,userName); return convertToDto(t); }
 * 
 * 
 * @PostMapping(value = { "/MarkAsDiseased/{treeID}/{userName}" })
 * 
 * 
 * public TreeDto MarkAsDiseasedWithID(
 * 
 * @PathVariable("treeID") int treeID,
 * 
 * @PathVariable("userName") String userName) throws InvalidInputException {
 * 
 * Tree t = service.getTreeByID(treeID);
 * 
 * service.MarkTreeAsDiseased(t,userName); return convertToDto(t); }
 * 
 * 
 * 
 * @PostMapping(value = { "/setTreeSpecies/{treeID}" })
 * 
 * 
 * public TreeDto setTreeSpecies(
 * 
 * @PathVariable("treeID") int treeID,
 * 
 * @RequestParam (name="species") TreeSpecies species) throws
 * InvalidInputException {
 * 
 * Tree t = service.getTreeByID(treeID);
 * 
 * t.setTreeSpecies(species); return convertToDto(t); }
 * 
 * @PostMapping(value = { "/setTreeLandType/{treeID}" }) public TreeDto
 * setTreeLandType(
 * 
 * @PathVariable("treeID") int treeID,
 * 
 * @RequestParam (name="landType") LandType landType) throws
 * InvalidInputException {
 * 
 * Tree t = service.getTreeByID(treeID);
 * 
 * t.setLandType(landType); return convertToDto(t); }
 * 
 * 
 * 
 * @PostMapping(value = { "/setTreeHeight/{treeID}" }) public TreeDto
 * setTreeHeight(
 * 
 * @PathVariable("treeID") int treeID,
 * 
 * @RequestParam (name="height") double height) throws InvalidInputException {
 * 
 * Tree t = service.getTreeByID(treeID);
 * 
 * t.setHeight(height); return convertToDto(t); }
 * 
 * 
 * 
 * @PostMapping(value = { "/setTreeDiameter/{treeID}" }) public TreeDto
 * setTreeDiameter(
 * 
 * @PathVariable("treeID") int treeID,
 * 
 * @RequestParam (name="diameter") double diameter) throws InvalidInputException
 * {
 * 
 * Tree t = service.getTreeByID(treeID);
 * 
 * t.setDiameter(diameter); return convertToDto(t); }
 * 
 * 
 * 
 * 
 * 
 * @PostMapping(value = { "/setTreeLongitude/{treeID}" }) public TreeDto
 * setTreeLongitude(
 * 
 * @PathVariable("treeID") int treeID,
 * 
 * @RequestParam (name="Longitude") double Longitude) throws
 * InvalidInputException {
 * 
 * Tree t = service.getTreeByID(treeID);
 * 
 * t.setLongitude(Longitude); return convertToDto(t); }
 * 
 * 
 * 
 * 
 * @PostMapping(value = { "/setTreeLatitude/{treeID}" }) public TreeDto
 * setTreeLatitude(
 * 
 * @PathVariable("treeID") int treeID,
 * 
 * @RequestParam (name="latitude") double latitude) throws InvalidInputException
 * {
 * 
 * Tree t = service.getTreeByID(treeID);
 * 
 * t.setLatitude(latitude); return convertToDto(t); }
 * 
 * 
 * }
 * 
 * 
 * 
 * 
 * 
 * 
 */
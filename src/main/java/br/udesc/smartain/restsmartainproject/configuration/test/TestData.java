package br.udesc.smartain.restsmartainproject.configuration.test;

import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CityComponent.City;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CityComponent.CityId;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CityComponent.CityService;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CountryComponent.Country;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CountryComponent.CountryService;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.FederativeUnitComponent.FederativeUnit;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.FederativeUnitComponent.FederativeUnitId;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.FederativeUnitComponent.FederativeUnitService;
import br.udesc.smartain.restsmartainproject.domain.glo.CustomerComponent.Customer;
import br.udesc.smartain.restsmartainproject.domain.glo.CustomerComponent.CustomerService;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnitService;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.User;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.UserGroup;
import br.udesc.smartain.restsmartainproject.domain.mhu.BrandComponent.Brand;
import br.udesc.smartain.restsmartainproject.domain.mhu.BrandComponent.BrandService;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.MachineService;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelComponent.MachineModel;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelComponent.MachineModelService;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelTypeComponent.MachineModelType;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelTypeComponent.MachineModelTypeService;
import br.udesc.smartain.restsmartainproject.domain.mhu.ManufacturerComponent.Manufacturer;
import br.udesc.smartain.restsmartainproject.domain.mhu.ManufacturerComponent.ManufacturerService;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProductionCellComponent.ProductionCell;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProductionCellComponent.ProductionCellService;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProfessionalComponent.Professional;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProfessionalComponent.ProfessionalService;
import br.udesc.smartain.restsmartainproject.domain.mhu.SectorComponent.Sector;
import br.udesc.smartain.restsmartainproject.domain.mhu.SectorComponent.SectorService;
import br.udesc.smartain.restsmartainproject.domain.mhu.UnitTypeComponent.UnitType;
import br.udesc.smartain.restsmartainproject.domain.mhu.UnitTypeComponent.UnitTypeService;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenanceTypeComponent.MaintenanceType;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenanceTypeComponent.MaintenanceTypeService;
import br.udesc.smartain.restsmartainproject.domain.mpp.OrderGenerationTypeComponent.OrderGenerationTypeService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceCauseComponent.ServiceCauseService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServicePriorityComponent.ServicePriority;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServicePriorityComponent.ServicePriorityService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSolicitationComponent.ServiceSolicitationService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSymptomComponent.ServiceSymptom;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSymptomComponent.ServiceSymptomService;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.UserGroupService;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.UserService;
import br.udesc.smartain.restsmartainproject.domain.types.DomainMachineModelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Profile(value = "test")
public class TestData implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private FederativeUnitService federativeUnitService;

    @Autowired
    private CityService cityService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UnitTypeService unitTypeService;

    @Autowired
    private ManufacturingUnitService manufacturingUnitService;

    @Autowired
    private SectorService sectorService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private MachineModelTypeService machineModelTypeService;

    @Autowired
    private ServiceSymptomService serviceSymptomService;

    @Autowired
    private MachineModelService machineModelService;

    @Autowired
    private ProductionCellService productionCellService;

    @Autowired
    private MachineService machineService;

    @Autowired
    private ProfessionalService professionalService;

    @Autowired
    private ServiceSolicitationService serviceSolicitationService;

    @Autowired
    private MaintenanceTypeService maintenanceTypeService;

    @Autowired
    private ServiceCauseService serviceCauseService;

    @Autowired
    private ServicePriorityService servicePriorityService;

    @Autowired
    private OrderGenerationTypeService orderGenerationTypeService;

    private List<Country> countries = new ArrayList<>();
    private List<FederativeUnit> units = new ArrayList<>();

    private List<City> cities = new ArrayList<>();

    private List<UnitType> types = new ArrayList<>();

    private List<ManufacturingUnit> manUnits = new ArrayList<>();

    private List<Sector> sectors = new ArrayList<>();

    private List<Brand> brands = new ArrayList<>();

    private List<Manufacturer> manufacturers = new ArrayList<>();

    private List<MachineModelType>  machineModelsTypes = new ArrayList<>();

    private List<MachineModel>  machineModels = new ArrayList<>();

    private List<ProductionCell>  productionCells = new ArrayList<>();

    private List<Machine> machines = new ArrayList<>();


    @Override
    public void run(String... args) throws Exception {
        makeCostumer();
        makeUsers(makeUserGroups());
        makeCountries();
        makeFederativeUnits();
        associateUnits();
        makeCities();

        makeManufacturingUnitTypes();
        makeManufacturingUnits();
        makeSectors();

        makeBrands();
        makeManufacturers();

        makeMachineModelTypes();
        makeMachineModels();
        makeProductionsCells();

        makeMachines();
    }

    public void associateUnits() {
        Country brasil = countryService.findByName("Brasil").get().get(0);
        for(FederativeUnit unit : units) {
            brasil.addUnit(unit);
        }
        countryService.save(brasil);
    }

    public List<UnitType> makeManufacturingUnitTypes() {
        this.types = Arrays.asList(
                new UnitType(1, 0, "Unidade Produtiva", "Unidade operacional, quando exercer atividades de produção ou venda de bens e/ou serviços destinados a terceiros"),
                new UnitType(2, 1,  "Sede", "Administração central da empresa, presidência, diretoria."),
                new UnitType(3, 2, "Escritório Administrativo", "Estabelecimento onde são exercidas atividades meramente administrativas, tais como: escritório de contato, setor de contabilidade, etc."),
                new UnitType(4,3, "Depósito Fechado", "Estabelecimento onde a empresa armazena mercadorias próprias destinadas à industrialização e/ou comercialização, no qual não se realizam vendas."),
                new UnitType(5, 4, "Almoxarifado", "Estabelecimento onde a empresa armazena artigos de consumo para uso próprio."),
                new UnitType(6, 5, "Oficina de Reparação", "Estabelecimento onde se efetua manutenção e reparação exclusivamente de bens do ativo fixo da própria empresa."),
                new UnitType(7, 6, "Garagem", "Para estacionamento de veículos próprios, uso exclusivo da empresa."),
                new UnitType(8, 7, "Unidade de abastecimento de combustíveis", "Exclusivamente para uso pela frota própria."),
                new UnitType(9, 8, "Ponto de exposição", "Local para exposição e demonstração de produtos próprios, sem realização de transações comerciais, tipo showroom."),
                new UnitType(10, 9, "Centro de treinamento", "Uso exclusivo da empresa, para realização de atividades de capacitação e treinamentos de recursos humanos."),
                new UnitType(11, 10, "Centro de Processamento de Dados", "Uso exclusivo da empresa, para realização de atividades na área de informática em geral."),
                new UnitType(12, 13, "Posto de Serviço", "Posto de Serviço"),
                new UnitType(13, 14, "Posto de Coleta", "Estabelecimento destinado a atender o público com o objetivo de recolher produtos/materiais/mercadorias/equipamentos/informações para posterior encaminhamento à unidade produtiva responsável por sua análise/processamento/beneficiamento/publicação. Ex: posto de coleta de anúncios classificados; posto de coleta de material para exames laboratoriais; posto de coleta de filmes fotográficos para revelação; posto de coleta de roupas para lavagem etc.")
        );
        unitTypeService.saveAll(types);
        return types;
    }

    public List<ManufacturingUnit> makeManufacturingUnits() {
        City cidade = cityService.findByCountryIdAndFederativeUnitIdAndCityId(23, 24, 1).get();
        UnitType type = unitTypeService.findById(1).get();
        this.manUnits = Arrays.asList(
                new ManufacturingUnit(1, customerService.getCustomer().get(), cidade, "Rua Tuiuti, 180, Centro, 89160045", "ABC-0001", RegisterState.ACTIVE, type)
        );
        for(ManufacturingUnit manUnit : manUnits) {
            manufacturingUnitService.save(manUnit);
        }
        return manUnits;
    }

    public List<Sector> makeSectors() {
        ManufacturingUnit unit = manufacturingUnitService.findById(1).get();
        this.sectors = Arrays.asList(
                new Sector(null, unit, "Beneficiamento", "Setor de beneficiamento da fabricação dos produtos", RegisterState.ACTIVE, LocalDateTime.now(), "PRD-001"),
                new Sector(null, unit, "Carregamento", "Setor de expedição e carregamento dos produtos", RegisterState.ACTIVE, LocalDateTime.now(), "CAR-001")
        );
        return sectorService.saveAll(sectors);
    }

    public void makeCostumer() {
        Customer customer = new Customer();
        customer.setMainCnpj("83891283000136");
        customer.setFantasyName("UDESC");
        customer.setCorporateReason("FUNDACAO UNIVERSIDADE DO ESTADO DE SC UDESC");

        customerService.save(customer);
    }

    public void makeUsers(List<UserGroup> groups) {
        userService.saveAll(
                Arrays.asList(
                        new User(null, groups.get(0), "matheusbus", "Matheus Buschermoehle","{bcrypt}$2a$10$zqMYMwxHWTtbNGWsg9qEPOVw0O5bYdRK9oj/g0Lirvpl.xw81DxmG", "matheus@smartain.com.br",  LocalDateTime.now(), LocalDateTime.now(), RegisterState.ACTIVE),
                        new User(null, groups.get(0), "adminadmin", "Admin Admin","adminadmin", "admin@smartain.com.br",  LocalDateTime.now(), LocalDateTime.now(), RegisterState.ACTIVE)
                )
        );
    }

    public List<UserGroup> makeUserGroups(){
        List<UserGroup> userGroups = Arrays.asList(
                new UserGroup(null, "Gestores de Manutenção", "Grupo que abrange os usuários gestores, que possuem acesso total ao sistema, priorizando os indicadores e relatórios.", RegisterState.ACTIVE, "ROLE_GESTOR"),
                new UserGroup(null, "Coordenadores de Manutenção", "Grupo que abrange os usuários coordenadores, com privilégios de cadastros e manuseio de integrações.", RegisterState.ACTIVE, "ROLE_COORDENADOR"),
                new UserGroup(null, "Planejadores e Controladores de Manutenção", "Grupo que abrange os usuários responsáveis pelos Planos de Manutenção, cronograma, e dados necessários pela manutenção.", RegisterState.ACTIVE, "ROLE_PCM"),
                new UserGroup(null, "Planejadores e Controladores de Produção", "Grupo que abrange os usuários da produção, que terão acesso ao sistema para visualizar o cronograma de manutenção e gerar novas solicitações de serviço.", RegisterState.ACTIVE, "ROLE_PCP"),
                new UserGroup(null, "Operadores de Manutenção", "Grupo que abrange os usuários manutentores, que serão responsáveis por executar ordens de serviço.", RegisterState.ACTIVE, "ROLE_OPERADOR")
        );

        userGroupService.saveAll(userGroups);
        return userGroups;
    }

    public List<Country> makeCountries() {
        this.countries = Arrays.asList(
                newCountry("Afeganistão"),
                newCountry("Albânia"),
                newCountry("Argélia"),
                newCountry("Andorra"),
                newCountry("Angola"),
                newCountry("Antígua e Barbada"),
                newCountry("Argentina"),
                newCountry("Armênia"),
                newCountry("Austrália"),
                newCountry("Áustria"),
                newCountry("Azerbaijão"),
                newCountry("Bahamas"),
                newCountry("Bahrein"),
                newCountry("Bangladesh"),
                newCountry("Barbados"),
                newCountry("Bielorrússia"),
                newCountry("Bélgica"),
                newCountry("Belize"),
                newCountry("Benin"),
                newCountry("Bolívia"),
                newCountry("Bósnia e Herzegovina"),
                newCountry("Botsuana"),
                newCountry("Brasil"),
                newCountry("Brunei"),
                newCountry("Bulgária"),
                newCountry("Burkina Faso"),
                newCountry("Burundi"),
                newCountry("Butão"),
                newCountry("Cabo Verde"),
                newCountry("Camarões"),
                newCountry("Camboja"),
                newCountry("Canadá"),
                newCountry("Catar"),
                newCountry("Cazaquistão"),
                newCountry("Chade"),
                newCountry("Chile"),
                newCountry("China"),
                newCountry("Chipre"),
                newCountry("Colômbia"),
                newCountry("Comores"),
                newCountry("Coreia do Norte"),
                newCountry("Coreia do Sul"),
                newCountry("Costa do Marfim"),
                newCountry("Costa Rica"),
                newCountry("Croácia"),
                newCountry("Cuba"),
                newCountry("Dinamarca"),
                newCountry("Djibuti"),
                newCountry("Dominica"),
                newCountry("Egito"),
                newCountry("El Salvador"),
                newCountry("Emirados Árabes Unidos"),
                newCountry("Equador"),
                newCountry("Eritreia"),
                newCountry("Eslováquia"),
                newCountry("Eslovênia"),
                newCountry("Espanha"),
                newCountry("Estados Unidos da América"),
                newCountry("Estônia"),
                newCountry("Eswatini"),
                newCountry("Etiópia"),
                newCountry("Fiji"),
                newCountry("Filipinas"),
                newCountry("Finlândia"),
                newCountry("França"),
                newCountry("Gabão"),
                newCountry("Gâmbia"),
                newCountry("Geórgia"),
                newCountry("Gana"),
                newCountry("Grécia"),
                newCountry("Granada"),
                newCountry("Guatemala"),
                newCountry("Guiana"),
                newCountry("Guiné"),
                newCountry("Guiné Equatorial"),
                newCountry("Guiné-Bissau"),
                newCountry("Haiti"),
                newCountry("Holanda"),
                newCountry("Honduras"),
                newCountry("Hungria"),
                newCountry("Iêmen"),
                newCountry("Ilhas Marshall"),
                newCountry("Ilhas Salomão"),
                newCountry("Índia"),
                newCountry("Indonésia"),
                newCountry("Irã"),
                newCountry("Iraque"),
                newCountry("Irlanda"),
                newCountry("Islândia"),
                newCountry("Israel"),
                newCountry("Itália"),
                newCountry("Jamaica"),
                newCountry("Japão"),
                newCountry("Jordânia"),
                newCountry("Kiribati"),
                newCountry("Kuwait"),
                newCountry("Laos"),
                newCountry("Lesoto"),
                newCountry("Letônia"),
                newCountry("Líbano"),
                newCountry("Libéria"),
                newCountry("Líbia"),
                newCountry("Liechtenstein"),
                newCountry("Lituânia"),
                newCountry("Luxemburgo"),
                newCountry("Macedônia do Norte"),
                newCountry("Madagascar"),
                newCountry("Malásia"),
                newCountry("Malawi"),
                newCountry("Maldivas"),
                newCountry("Mali"),
                newCountry("Malta"),
                newCountry("Marrocos"),
                newCountry("Maurício"),
                newCountry("Mauritânia"),
                newCountry("México"),
                newCountry("Micronésia"),
                newCountry("Moçambique"),
                newCountry("Moldávia"),
                newCountry("Mônaco"),
                newCountry("Mongólia"),
                newCountry("Montenegro"),
                newCountry("Namíbia"),
                newCountry("Nauru"),
                newCountry("Nepal"),
                newCountry("Nicarágua"),
                newCountry("Níger"),
                newCountry("Nigéria"),
                newCountry("Niue"),
                newCountry("Noruega"),
                newCountry("Nova Zelândia"),
                newCountry("Omã"),
                newCountry("Palau"),
                newCountry("Panamá"),
                newCountry("Papua Nova Guiné"),
                newCountry("Paquistão"),
                newCountry("Paraguai"),
                newCountry("Peru"),
                newCountry("Polônia"),
                newCountry("Portugal"),
                newCountry("Quênia"),
                newCountry("Quirguistão"),
                newCountry("Quiribati"),
                newCountry("Reino Unido"),
                newCountry("República Centro-Africana"),
                newCountry("República Dominicana"),
                newCountry("República Tcheca"),
                newCountry("Romênia"),
                newCountry("Ruanda"),
                newCountry("Rússia"),
                newCountry("Samoa"),
                newCountry("San Marino"),
                newCountry("Santa Lúcia"),
                newCountry("São Cristóvão e Nevis"),
                newCountry("São Tomé e Príncipe"),
                newCountry("São Vicente e Granadinas"),
                newCountry("Seicheles"),
                newCountry("Senegal"),
                newCountry("Serra Leoa"),
                newCountry("Sérvia"),
                newCountry("Singapura"),
                newCountry("Síria"),
                newCountry("Somália"),
                newCountry("Sri Lanka"),
                newCountry("Suazilândia"),
                newCountry("Sudão"),
                newCountry("Sudão do Sul"),
                newCountry("Suécia"),
                newCountry("Suíça"),
                newCountry("Suriname"),
                newCountry("Tailândia"),
                newCountry("Taiwan"),
                newCountry("Tajiquistão"),
                newCountry("Tanzânia"),
                newCountry("Timor-Leste"),
                newCountry("Togo"),
                newCountry("Tonga"),
                newCountry("Trinidad e Tobago"),
                newCountry("Tunísia"),
                newCountry("Turcomenistão"),
                newCountry("Turquia"),
                newCountry("Tuvalu"),
                newCountry("Ucrânia"),
                newCountry("Uganda"),
                newCountry("Uruguai"),
                newCountry("Uzbequistão"),
                newCountry("Vanuatu"),
                newCountry("Vaticano"),
                newCountry("Venezuela"),
                newCountry("Vietnã"),
                newCountry("Zâmbia"),
                newCountry("Zimbábue")
        );
        countryService.saveAll(countries);
        return countries;
    }

    public Country newCountry(String name) {
        return new Country(null, name);
    }

    public List<FederativeUnit> makeFederativeUnits() {
        Country brasil = countryService.findByName("Brasil").get().get(0);
        this.units = Arrays.asList(
                new FederativeUnit(new FederativeUnitId(1, brasil), "Acre"),
                new FederativeUnit(new FederativeUnitId(2, brasil), "Alagoas"),
                new FederativeUnit(new FederativeUnitId(3, brasil), "Amapá"),
                new FederativeUnit(new FederativeUnitId(4, brasil), "Amazonas"),
                new FederativeUnit(new FederativeUnitId(5, brasil), "Bahia"),
                new FederativeUnit(new FederativeUnitId(6, brasil), "Ceará"),
                new FederativeUnit(new FederativeUnitId(7, brasil), "Distrito Federal"),
                new FederativeUnit(new FederativeUnitId(8, brasil), "Espírito Santo"),
                new FederativeUnit(new FederativeUnitId(9, brasil), "Goiás"),
                new FederativeUnit(new FederativeUnitId(10, brasil), "Maranhão"),
                new FederativeUnit(new FederativeUnitId(11, brasil), "Mato Grosso"),
                new FederativeUnit(new FederativeUnitId(12, brasil), "Mato Grosso do Sul"),
                new FederativeUnit(new FederativeUnitId(13, brasil), "Minas Gerais"),
                new FederativeUnit(new FederativeUnitId(14, brasil), "Pará"),
                new FederativeUnit(new FederativeUnitId(15, brasil), "Paraíba"),
                new FederativeUnit(new FederativeUnitId(16, brasil), "Paraná"),
                new FederativeUnit(new FederativeUnitId(17, brasil), "Pernambuco"),
                new FederativeUnit(new FederativeUnitId(18, brasil), "Piauí"),
                new FederativeUnit(new FederativeUnitId(19, brasil), "Rio de Janeiro"),
                new FederativeUnit(new FederativeUnitId(20, brasil), "Rio Grande do Norte"),
                new FederativeUnit(new FederativeUnitId(21, brasil), "Rio Grande do Sul"),
                new FederativeUnit(new FederativeUnitId(22, brasil), "Rondônia"),
                new FederativeUnit(new FederativeUnitId(23, brasil), "Roraima"),
                new FederativeUnit(new FederativeUnitId(24, brasil), "Santa Catarina"),
                new FederativeUnit(new FederativeUnitId(25, brasil), "São Paulo"),
                new FederativeUnit(new FederativeUnitId(26, brasil), "Sergipe"),
                new FederativeUnit(new FederativeUnitId(27, brasil), "Tocantins")
        );
        federativeUnitService.saveAll(units);
        return units;
    }

    public List<City> makeCities() {
        FederativeUnit unit = federativeUnitService.findByName("catarina").get().get(0);
        this.cities = Arrays.asList(
                new City(new CityId(1, unit), "Rio do Sul"),
                new City(new CityId(1, unit), "Ibirama")
        );
        cityService.saveAll(cities);
        return cities;
    }

    public List<Brand> makeBrands() {
        this.brands = Arrays.asList(
                new Brand(null, "WEG", LocalDate.now(), userService.findAllUsers().get().get(0), RegisterState.ACTIVE),
                new Brand(null, "NSK", LocalDate.now(), userService.findAllUsers().get().get(0), RegisterState.ACTIVE)
        );
        for(Brand brand : brands) {
            brandService.save(brand);
        }
        return brands;
    }

    public List<Manufacturer> makeManufacturers() {
        this.manufacturers = Arrays.asList(
                new Manufacturer(null, "Industrial Pagé LTDA", "92412168000122", "482882012", "page@comercial.com.br",  RegisterState.ACTIVE),
                new Manufacturer(null, "WEG S.A.", "84429695000111", "5130262026", "weg@comercial.com.br",  RegisterState.ACTIVE)
        );
        for(Manufacturer manufacturer : this.manufacturers) {
            manufacturerService.save(manufacturer);
        }
        return manufacturers;
    }

    public List<MachineModelType> makeMachineModelTypes() {
        this.machineModelsTypes = Arrays.asList(
                new MachineModelType((Integer) null, "CNC", (short) 1, RegisterState.ACTIVE),
                new MachineModelType((Integer) null, "Manual", (short) 1, RegisterState.ACTIVE)
        );
        for(MachineModelType machineModelType : machineModelsTypes) {
            machineModelTypeService.save(machineModelType);
        }
        return machineModelsTypes;
    }

    public List<MachineModel> makeMachineModels() {
        this.machineModels = Arrays.asList(
                new MachineModel((Integer) null, this.manufacturers.get(1), "CNC de Cabeçote Fixo", "Largura: 3.2m, Comprimento: 5.0m", machineModelsTypes.get(0), RegisterState.ACTIVE),
                new MachineModel((Integer) null, this.manufacturers.get(1) , "Torno Manual", "Largura: 2.0m, Comprimento: 3.3m", machineModelsTypes.get(0), RegisterState.ACTIVE)
        );
        for(MachineModel machineModel : machineModels) {
            machineModelService.save(machineModel);
        }
        return machineModels;
    }

    public List<ProductionCell> makeProductionsCells() {
        this.productionCells = Arrays.asList(
                new ProductionCell((Integer) null, "Celula 1", "Celula de Tuchos", manufacturingUnitService.findById(1).orElse(null),  RegisterState.ACTIVE, LocalDateTime.now(), sectorService.findById(1).orElse(null), "CEL-001")
        );
        for(ProductionCell productionCell : productionCells) {
            productionCellService.save(productionCell);
        }
        return productionCells;
    }

    public List<Machine> makeMachines() {
        Machine machine = new Machine();
        ManufacturingUnit unit = manufacturingUnitService.findById(1).get();
        MachineModel machineModel = machineModelService.findById(1).get();
        Sector sector = sectorService.findById(1).get();
        ProductionCell productionCell = productionCellService.findById(1).get();


        machine.setUnit(unit);
        machine.setSector(sector);
        machine.setProductionCell(productionCell);
        machine.setStatus(RegisterState.ACTIVE);
        machine.setTag("MAQ-001");
        machine.setTechnicalData("Dados técnicos da máquina: 4000kg.");
        machine.setWarrantyExpDate(LocalDate.of(2025, Month.APRIL, 15));
        machine.setWarranty((short) 1);
        machine.setMachineModel(machineModel);
        machine.setAcquisitionDate(LocalDate.of(2022, Month.APRIL, 15));
        machine.setCreatedDate(LocalDate.now().atStartOfDay());

        this.machines = Arrays.asList(machine);

        for (Machine mac : this.machines) {
            machineService.save(machine);
        }
        return this.machines;
    }
}

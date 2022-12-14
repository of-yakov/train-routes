package ru.vsu.porkhunov.trainroutes;

import ru.vsu.porkhunov.trainroutes.entity.Route;
import ru.vsu.porkhunov.trainroutes.entity.Station;
import ru.vsu.porkhunov.trainroutes.entity.Train;
import ru.vsu.porkhunov.trainroutes.entity.Waypoint;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.RouteExtractor;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.StationExtractor;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.TrainExtractor;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.WaypointExtractor;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.impl.RouteExtractorImpl;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.impl.StationExtractorImpl;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.impl.TrainExtractorImpl;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.impl.WaypointExtractorImpl;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.RouteWrapper;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.StationWrapper;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.TrainWrapper;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.WaypointWrapper;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.impl.RouteWrapperImpl;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.impl.StationWrapperImpl;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.impl.TrainWrapperImpl;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.impl.WaypointWrapperImpl;
import ru.vsu.porkhunov.trainroutes.persistence.provider.connection.ConnectionProvider;
import ru.vsu.porkhunov.trainroutes.persistence.repository.RouteRepository;
import ru.vsu.porkhunov.trainroutes.persistence.repository.StationRepository;
import ru.vsu.porkhunov.trainroutes.persistence.repository.TrainRepository;
import ru.vsu.porkhunov.trainroutes.persistence.repository.WaypointRepository;
import ru.vsu.porkhunov.trainroutes.persistence.repository.remote.impl.RouteRepositoryImpl;
import ru.vsu.porkhunov.trainroutes.persistence.repository.remote.impl.StationRepositoryImpl;
import ru.vsu.porkhunov.trainroutes.persistence.repository.remote.impl.TrainRepositoryImpl;
import ru.vsu.porkhunov.trainroutes.persistence.repository.remote.impl.WaypointRepositoryImpl;
import ru.vsu.porkhunov.trainroutes.persistence.util.RepositoryInitializer;
import ru.vsu.porkhunov.trainroutes.service.RouteService;
import ru.vsu.porkhunov.trainroutes.service.StationService;
import ru.vsu.porkhunov.trainroutes.service.TrainService;
import ru.vsu.porkhunov.trainroutes.service.WaypointService;
import ru.vsu.porkhunov.trainroutes.service.impl.RouteServiceImpl;
import ru.vsu.porkhunov.trainroutes.service.impl.StationServiceImpl;
import ru.vsu.porkhunov.trainroutes.service.impl.TrainServiceImpl;
import ru.vsu.porkhunov.trainroutes.service.impl.WaypointServiceImpl;
import ru.vsu.porkhunov.trainroutes.ui.ConsoleUi;
import ru.vsu.porkhunov.trainroutes.ui.command.ConsoleCommand;
import ru.vsu.porkhunov.trainroutes.ui.command.impl.ReferenceCommand;
import ru.vsu.porkhunov.trainroutes.ui.command.impl.route.*;
import ru.vsu.porkhunov.trainroutes.ui.command.impl.station.*;
import ru.vsu.porkhunov.trainroutes.ui.command.impl.train.*;
import ru.vsu.porkhunov.trainroutes.ui.command.impl.waypoint.*;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        /* реализация in memory репозиториев

        LongIdProvider routeIdProvider = new IdentityLongIdProvider();
        LongIdProvider waypointIdProvider = new IdentityLongIdProvider();
        LongIdProvider stationIdProvider = new IdentityLongIdProvider();
        LongIdProvider trainIdProvider = new IdentityLongIdProvider();

        RepositoryInitializer<Route> routes = getInitialRoutes();
        RepositoryInitializer<Waypoint> waypoints = getInitialWaypoints();
        RepositoryInitializer<Station> stations = getInitialStations();
        RepositoryInitializer<Train> trains = getInitialTrains();

        RouteRepository routeRepository = new InMemoryRouteRepository(routeIdProvider, routes);
        WaypointRepository waypointRepository = new InMemoryWaypointRepository(waypointIdProvider, waypoints);
        StationRepository stationRepository = new InMemoryStationRepository(stationIdProvider, stations);
        TrainRepository trainRepository = new InMemoryTrainRepository(trainIdProvider, trains);

        */

        RouteWrapper routeWrapper = new RouteWrapperImpl();
        WaypointWrapper waypointWrapper = new WaypointWrapperImpl();
        StationWrapper stationWrapper = new StationWrapperImpl();
        TrainWrapper trainWrapper = new TrainWrapperImpl();

        RouteExtractor routeExtractor = new RouteExtractorImpl();
        WaypointExtractor waypointExtractor = new WaypointExtractorImpl();
        StationExtractor stationExtractor = new StationExtractorImpl();
        TrainExtractor trainExtractor = new TrainExtractorImpl();

        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

        RouteRepository routeRepository = new RouteRepositoryImpl(
                routeWrapper, routeExtractor, connectionProvider);

        WaypointRepository waypointRepository = new WaypointRepositoryImpl(
                waypointWrapper, waypointExtractor, connectionProvider);

        StationRepository stationRepository = new StationRepositoryImpl(
                stationWrapper, stationExtractor, connectionProvider);

        TrainRepository trainRepository = new TrainRepositoryImpl(
                trainWrapper, trainExtractor, connectionProvider);

        RouteService routeService = new RouteServiceImpl(routeRepository);
        WaypointService waypointService = new WaypointServiceImpl(waypointRepository);
        StationService stationService = new StationServiceImpl(stationRepository);
        TrainService trainService = new TrainServiceImpl(trainRepository);

        Scanner scanner = new Scanner(System.in);

        Map<Integer, ConsoleCommand> commands = new HashMap<>();
        commands.put(0, new ReferenceCommand());

        commands.put(1, new RouteAddCommand(routeService, scanner));
        commands.put(2, new RouteDeleteByIdCommand(routeService, scanner));
        commands.put(3, new RouteExistsByIdCommand(routeService, scanner));
        commands.put(4, new RouteFindAllCommand(routeService, scanner));
        commands.put(5, new RouteFindAllByTrainIdCommand(routeService, scanner));
        commands.put(6, new RouteFindByIdCommand(routeService, scanner));
        commands.put(7, new RouteUpdateByIdCommand(routeService, scanner));

        commands.put(8, new WaypointAddCommand(waypointService, scanner));
        commands.put(9, new WaypointDeleteByIdCommand(waypointService, scanner));
        commands.put(10, new WaypointExistsByIdCommand(waypointService, scanner));
        commands.put(11, new WaypointFindAllCommand(waypointService, scanner));
        commands.put(12, new WaypointFindAllByRouteIdWithSortByArrivesAtCommand(waypointService, scanner));
        commands.put(13, new WaypointFindByIdCommand(waypointService, scanner));
        commands.put(14, new WaypointUpdateByIdCommand(waypointService, scanner));

        commands.put(15, new StationAddCommand(stationService, scanner));
        commands.put(16, new StationDeleteByIdCommand(stationService, scanner));
        commands.put(17, new StationExistsByIdCommand(stationService, scanner));
        commands.put(18, new StationFindAllCommand(stationService, scanner));
        commands.put(19, new StationFindByIdCommand(stationService, scanner));
        commands.put(20, new StationUpdateByIdCommand(stationService, scanner));

        commands.put(21, new TrainAddCommand(trainService, scanner));
        commands.put(22, new TrainDeleteByIdCommand(trainService, scanner));
        commands.put(23, new TrainExistsByIdCommand(trainService, scanner));
        commands.put(24, new TrainFindAllCommand(trainService, scanner));
        commands.put(25, new TrainFindByIdCommand(trainService, scanner));
        commands.put(26, new TrainUpdateByIdCommand(trainService, scanner));

        ConsoleUi consoleUi = new ConsoleUi(commands, scanner);
        consoleUi.start();
    }


    private static RepositoryInitializer<Route> getInitialRoutes() {
        List<Route> routes = new ArrayList<>();

        /* заполнение локальной бд начальными маршрутами */

        routes.add(new Route("1 route", 1L, 1L, 1L));
        routes.add(new Route("2 route", 2L, 2L, 2L));
        routes.add(new Route("3 route", 3L, 3L, 3L));

        return () -> routes;
    }

    private static RepositoryInitializer<Waypoint> getInitialWaypoints() {
        List<Waypoint> waypoints = new ArrayList<>();

        /* заполнение локальной бд начальными точками маршрутов */

        return () -> waypoints;
    }

    private static RepositoryInitializer<Station> getInitialStations() {
        List<Station> stations = new ArrayList<>();

        /* заполнение локальной бд начальными станциями */

        return () -> stations;
    }

    private static RepositoryInitializer<Train> getInitialTrains() {
        List<Train> trains = new ArrayList<>();

        /* заполнение локальной бд начальными станциями */

        return () -> trains;
    }
}

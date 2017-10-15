package my.pinkyo.demo;

import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import my.pinkyo.demo.Dao.UserDao;
import my.pinkyo.demo.entity.UserEntity;
import my.pinkyo.demo.resource.UserResource;

public class DemoApplication extends Application<DemoConfiguration> {
    private final HibernateBundle<DemoConfiguration> hibernate = new HibernateBundle<DemoConfiguration>(UserEntity.class) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(DemoConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<DemoConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(DemoConfiguration configuration, Environment environment) throws Exception {
        UserDao dao = new UserDao(hibernate.getSessionFactory());
        UserResource resource = new UserResource(dao);
        environment.jersey().register(resource);
    }

    @Override
    public String getName() {
        return "Demo application";
    }

    public static void main(String[] args) throws Exception {
        new DemoApplication().run(args);
    }
}

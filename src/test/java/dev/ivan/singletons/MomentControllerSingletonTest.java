package dev.ivan.singletons;

import dev.ivan.controllers.MomentController;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class MomentControllerSingletonTest {

    @Test
    void getInstance_shouldReturnSameInstance() {
        MomentController instance1 = MomentControllerSingleton.getInstance();
        MomentController instance2 = MomentControllerSingleton.getInstance();


        assertThat(instance1, is(notNullValue()));
        assertThat(instance1, is(sameInstance(instance2)));
    }

    @Test
    void getInstance_shouldReturnMomentControllerType() {
        MomentController instance = MomentControllerSingleton.getInstance();
        assertThat(instance, is(instanceOf(MomentController.class)));
    }
}

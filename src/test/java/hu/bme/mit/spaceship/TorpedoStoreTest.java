package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.validateMockitoUsage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {-2, -1, 0, 1, 2})
    void fireIllegalWithEmptySource(int numberOfTorpedos){
        TorpedoStore store = new TorpedoStore(0);

        assertTrue(store.isEmpty());
        assertEquals(0, store.getTorpedoCount());

        assertThrows(IllegalArgumentException.class, () -> {
            store.fire(numberOfTorpedos);
        });
        
        assertTrue(store.isEmpty());
        assertEquals(0, store.getTorpedoCount());
    }

    @ParameterizedTest
    @ValueSource(ints = {-2, -1, 0, 2, 5})
    void fireIllegalWithASingleTorpedoStore(int numberOfTorpedos){
        TorpedoStore store = new TorpedoStore(1);
        
        assertFalse(store.isEmpty());
        assertEquals(1, store.getTorpedoCount());

        assertThrows(IllegalArgumentException.class, () -> {
            store.fire(numberOfTorpedos);
        });

        assertFalse(store.isEmpty());
        assertEquals(1, store.getTorpedoCount());
    }

    @Test
    void fireValidWithASingleTorpedoStore(){
        TorpedoStore store = new TorpedoStore(1);

        assertFalse(store.isEmpty());
        assertEquals(1, store.getTorpedoCount());

        assertEquals(true, store.fire(1));

        assertTrue(store.isEmpty());
        assertEquals(0, store.getTorpedoCount());
    }

    @ParameterizedTest
    @ValueSource(ints = {-2, -1, 0, 4, 5})
    void fireIllegalWithAMultipleTorpedoesStore(int numberOfTorpedos){
        TorpedoStore store = new TorpedoStore(3);

        assertFalse(store.isEmpty());
        assertEquals(3, store.getTorpedoCount());

        assertThrows(IllegalArgumentException.class, () -> {
            store.fire(numberOfTorpedos);
        });
        
        assertFalse(store.isEmpty());
        assertEquals(3, store.getTorpedoCount());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void fireValidWithAMultipleTorpedoesStore(int numberOfTorpedos){
        TorpedoStore store = new TorpedoStore(3);

        assertFalse(store.isEmpty());
        assertEquals(3, store.getTorpedoCount());

        assertEquals(true, store.fire(numberOfTorpedos));

        if(numberOfTorpedos == 3){
            assertTrue(store.isEmpty());
        }else{
            assertFalse(store.isEmpty());
        }
        assertEquals(3 - numberOfTorpedos, store.getTorpedoCount());
    }
}

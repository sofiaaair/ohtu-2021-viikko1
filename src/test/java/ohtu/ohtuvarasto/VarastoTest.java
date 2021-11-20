package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void liikaOttaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(5);
        
        double saatuMaara = varasto.otaVarastosta(8);
        
        assertEquals(5, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void liikaLaittaminenMeneeHukkaan() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(8);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenPalauttaaOikein() {
        varasto.lisaaVarastoon(8);
        
        double saatuMaara = varasto.otaVarastosta(-1);
        
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaaminenLisaaNolla() {
        varasto.lisaaVarastoon(-5);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenTilavuusMuuttuuNollaksi() {
        Varasto nollavarasto = new Varasto(-1);
        
        assertEquals(0, nollavarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoAlkusaldollaPalauttaaOikein() {
        Varasto alkusaldovarasto = new Varasto(10, 5);
        
        assertEquals(10, alkusaldovarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenAlkusaldovarastonTilavuusOnNolla() {
        Varasto alkusaldovarasto = new Varasto(-1, -10);
        
        assertEquals(0, alkusaldovarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenAlkusaldovarastonSaldoOnNolla() {
        Varasto alkusaldovarasto = new Varasto(10, -5);
        
        assertEquals(0, alkusaldovarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void liikaAlkusaldoMeneeHukkaan() {
        Varasto alkusaldovarasto = new Varasto(10, 15);
        
        assertEquals(10, alkusaldovarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    
    public void toStringPalauttaaOikein() {
        varasto.lisaaVarastoon(1);
        String teksti = varasto.toString();
        
        assertEquals("saldo = 1.0, vielä tilaa 9.0", teksti);
    }
    
    
    
}
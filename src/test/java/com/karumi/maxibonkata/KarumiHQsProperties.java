package com.karumi.maxibonkata;

/**
 * Created by libanez on 01/04/2017.
 */

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

@RunWith(JUnitQuickcheck.class) public class KarumiHQsProperties {
    private static final int ANY_NUMBER = 2;
    private static final String ANY_NAME = "Name";
    private Chat chat;
    private KarumiHQs karumiHQs;

    @Before public void setUp(){
        karumiHQs = new KarumiHQs();
    }

    @Property public void shouldInvokeToTheChatWhenTheFridgeIsEmpty(@From(HungryDevelopersGenerator.class) Developer developer){
        Chat chat = mock(Chat.class);

        karumiHQs = new KarumiHQs(chat);

        karumiHQs.openFridge(developer);

        Mockito.verify(chat).sendMessage(any());
    }

    @Property public void theNumberOfMaxibonsCanNeverBeLowerThanTwo(@From(DevelopersGenerator.class) Developer developer){

        karumiHQs = new KarumiHQs();

        System.out.println(developer.getPresentation());

        karumiHQs.openFridge(developer);

        assertTrue(karumiHQs.getMaxibonsLeft() > 2);
    }

    @Property public void theNumberOfMaxibonsCanNeverBeLowerThanTwo(
            List<@From(DevelopersGenerator.class)  Developer> developers) {

        karumiHQs = new KarumiHQs();

        for(Developer developer : developers){
            System.out.println(developer.getPresentation());

            karumiHQs.openFridge(developer);
        }

        assertTrue(karumiHQs.getMaxibonsLeft() > 2);
    }


    @Property public void theNameOfTheDeveloperIsTheOneIPassAsAParameter(String developerName){
        System.out.println(developerName);

        Developer developer = new Developer(developerName, ANY_NUMBER);

        assertTrue(developer.getName().equals(developerName));
    }

    @Property public void theNumberOfMaxibonsOfEveryDeveloperIsAlwaysBiggerThanZero(int numMaxibons){
        System.out.println(numMaxibons);

        Developer developer = new Developer(ANY_NAME, numMaxibons);

        assertTrue(developer.getNumberOfMaxibonsToGrab() >= 0);
    }

}

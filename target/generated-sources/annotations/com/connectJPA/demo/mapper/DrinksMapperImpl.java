package com.connectJPA.demo.mapper;

import com.connectJPA.demo.dto.request.DrinksCreationRequest;
import com.connectJPA.demo.dto.request.DrinksUpdateRequest;
import com.connectJPA.demo.dto.response.DrinksResponse;
import com.connectJPA.demo.entity.Drinks;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-08T15:24:49+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class DrinksMapperImpl implements DrinksMapper {

    @Override
    public DrinksResponse toDrinksResponse(Drinks drinks) {
        if ( drinks == null ) {
            return null;
        }

        DrinksResponse.DrinksResponseBuilder drinksResponse = DrinksResponse.builder();

        drinksResponse.id( drinks.getId() );
        drinksResponse.name( drinks.getName() );
        drinksResponse.price( drinks.getPrice() );
        drinksResponse.ingredients( drinks.getIngredients() );
        drinksResponse.type( drinks.getType() );
        drinksResponse.rating( drinks.getRating() );
        drinksResponse.imageUrl( drinks.getImageUrl() );

        return drinksResponse.build();
    }

    @Override
    public Drinks toDrinks(DrinksCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Drinks.DrinksBuilder drinks = Drinks.builder();

        drinks.name( request.getName() );
        drinks.price( request.getPrice() );
        drinks.ingredients( request.getIngredients() );
        drinks.description( request.getDescription() );

        return drinks.build();
    }

    @Override
    public void updateDrinks(Drinks drinks, DrinksUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        drinks.setName( request.getName() );
        drinks.setPrice( request.getPrice() );
        drinks.setIngredients( request.getIngredients() );
        drinks.setDescription( request.getDescription() );
        drinks.setRating( request.getRating() );
    }
}

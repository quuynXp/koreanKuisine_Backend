package com.connectJPA.demo.mapper;

import com.connectJPA.demo.dto.request.DishCreationRequest;
import com.connectJPA.demo.dto.request.DrinksCreationRequest;
import com.connectJPA.demo.dto.response.DishResponse;
import com.connectJPA.demo.dto.response.DrinksResponse;
import com.connectJPA.demo.entity.Dish;
import com.connectJPA.demo.entity.Drinks;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-08T15:24:50+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public DishResponse toDishResponse(Dish dish) {
        if ( dish == null ) {
            return null;
        }

        DishResponse.DishResponseBuilder dishResponse = DishResponse.builder();

        dishResponse.id( dish.getId() );
        dishResponse.name( dish.getName() );
        dishResponse.price( dish.getPrice() );
        dishResponse.ingredients( dish.getIngredients() );
        dishResponse.type( dish.getType() );
        dishResponse.rating( dish.getRating() );
        dishResponse.imageUrl( dish.getImageUrl() );

        return dishResponse.build();
    }

    @Override
    public Dish toDish(DishCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Dish.DishBuilder dish = Dish.builder();

        dish.name( request.getName() );
        dish.price( request.getPrice() );
        dish.ingredients( request.getIngredients() );
        dish.description( request.getDescription() );

        return dish.build();
    }

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
    public Dish copyDish(Dish source) {
        if ( source == null ) {
            return null;
        }

        Dish.DishBuilder dish = Dish.builder();

        dish.id( source.getId() );
        dish.name( source.getName() );
        dish.price( source.getPrice() );
        dish.ingredients( source.getIngredients() );
        dish.description( source.getDescription() );
        dish.type( source.getType() );
        dish.rating( source.getRating() );
        dish.imageUrl( source.getImageUrl() );
        dish.images( source.getImages() );

        return dish.build();
    }

    @Override
    public Drinks copyDrinks(Drinks source) {
        if ( source == null ) {
            return null;
        }

        Drinks.DrinksBuilder drinks = Drinks.builder();

        drinks.id( source.getId() );
        drinks.name( source.getName() );
        drinks.price( source.getPrice() );
        drinks.ingredients( source.getIngredients() );
        drinks.description( source.getDescription() );
        drinks.type( source.getType() );
        drinks.rating( source.getRating() );
        drinks.imageUrl( source.getImageUrl() );
        drinks.images( source.getImages() );

        return drinks.build();
    }
}

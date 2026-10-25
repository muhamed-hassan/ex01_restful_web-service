package app.web.controllers.transformers;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseTransformer<E, M> {
	
	@Autowired
	protected SimpleDateFormat simpleDateFormat;
	
	// assume both objects has no depth more than one simple field at most
	public M toModel(E entity) {
		
		M model = null;
		try {
			
			model = getClassOfUsedModel().getDeclaredConstructor().newInstance();

			Field[] fieldsOfEntityUnderTransformation = entity.getClass().getDeclaredFields();
			for (int cursor = 0; cursor < fieldsOfEntityUnderTransformation.length; cursor++) {

				Field sourceField = fieldsOfEntityUnderTransformation[cursor];
				sourceField.setAccessible(true); 
				Object sourceValue = sourceField.get(entity);
				
				Field destinationField = getClassOfUsedModel().getDeclaredField(sourceField.getName());
				destinationField.setAccessible(true);
				destinationField.set(model, sourceValue);
			}

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		
		return model;
	}
	
	// assume both objects has no depth more than one simple field at most
	public ArrayList<M> toModels(ArrayList<E> entities) {
		
		ArrayList<M> models = new ArrayList<M>();
		
		for (int cursor = 0; cursor < entities.size(); cursor++) {
			
			M currentModel = toModel(entities.get(cursor));
			
			models.add(currentModel);			
		}
		
		return models;
	}
	
	public Date toDate(String textualValueOfDate) {
		
		Date date = null;
		try {
			
			date = simpleDateFormat.parse(textualValueOfDate);
			
		} catch (ParseException e) {}
		
		return date;
	}	
	
	/* ******************************************************************************************************************************* */

	private Class<M> getClassOfUsedModel() {
		
		return (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

}

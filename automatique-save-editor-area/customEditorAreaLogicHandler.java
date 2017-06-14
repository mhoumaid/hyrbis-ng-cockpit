package backoffice.customization;
import com.hybris.cockpitng.dataaccess.facades.object.exceptions.ObjectSavingException;
import com.hybris.cockpitng.engine.WidgetInstanceManager;
import com.hybris.cockpitng.validation.ValidationContext;
import com.hybris.cockpitng.validation.model.ValidationInfo;
import com.hybris.cockpitng.widgets.baseeditorarea.DefaultEditorAreaLogicHandler;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class CustomEditorAreaLogicHandler extends DefaultEditorAreaLogicHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CustomEditorAreaLogicHandler.class);

    @Override
    public List<ValidationInfo> performValidation(WidgetInstanceManager widgetInstanceManager, Object currentObject, List<String> qualifiers, ValidationContext validationContext) {
        final List<ValidationInfo> validationsInfos = super.performValidation(widgetInstanceManager, currentObject, qualifiers, validationContext);
        if(CollectionUtils.isEmpty(validationsInfos)) {
            try {
                super.performSave(widgetInstanceManager, currentObject);
            } catch (ObjectSavingException ex) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug(ex.getMessage(), ex);
                }
            }
        }

        return validationsInfos;
    }
}

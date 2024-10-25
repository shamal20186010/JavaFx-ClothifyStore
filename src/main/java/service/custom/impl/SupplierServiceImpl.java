package service.custom.impl;

import dto.Supplier;
import entity.SupplierEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.SupplierDao;
import service.custom.SupplierService;
import util.DaoType;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierServiceImpl implements SupplierService {

    SupplierDao supplierDao = DaoFactory.getInstance().getServiceType(DaoType.SUPPLIER);

    public boolean addSupplier(Supplier supplier){
        SupplierEntity supplierEntity = new ModelMapper().map(supplier, SupplierEntity.class);
        return supplierDao.save(supplierEntity);
    }
    public boolean updateSupplier(Supplier supplier){
        SupplierEntity supplierEntity = new ModelMapper().map(supplier, SupplierEntity.class);
        return supplierDao.update(supplierEntity);
    }
    public boolean deleteSupplier(String id){
        return supplierDao.delete(id);
    }
    public ObservableList<Supplier> getAll(){
        ObservableList<Supplier> suppliersList = FXCollections.observableArrayList();
        supplierDao.getAll().forEach(supplierEntity -> {
            suppliersList.add(new ModelMapper().map(supplierEntity, Supplier.class));
        });
        System.out.println(suppliersList);
        return suppliersList;
    }

    @Override
    public ObservableList<String> getSupplierNames() {
        ObservableList<String> supplierNameList = FXCollections.observableArrayList();
        supplierDao.getAll().forEach(supplierEntity -> {
            supplierNameList.add(supplierEntity.getName());
        });
        return supplierNameList;
    }

    public ObservableList<String> getSupplierIds(){
        ObservableList<String> supplierIdList = FXCollections.observableArrayList();
        supplierDao.getAll().forEach(supplierEntity -> {
            supplierIdList.add(supplierEntity.getId());
        });
        return supplierIdList;
    }

    @Override
    public String generateId() {
        List<String> supplierIdList = getSupplierIds();
        if (!supplierIdList.isEmpty()) {
            String last = supplierIdList.get((supplierIdList.size())-1);
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(last);
            Integer id = null;
            while (m.find()) {
                id = Integer.parseInt(m.group());
            }
            return String.format("S%03d",(id+1));
        }else {
            return "S001";
        }
    }
}

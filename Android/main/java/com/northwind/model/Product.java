
package com.northwind.model;

import android.util.Log;

import java.util.ArrayList;

/**
 * Product object structured to match the Northwind OData service
 * <p></p>
 * The EntityType Name for the Northwind service we are using is "Products"
 * <p></p>
 * In this URL: <a href="http://services.odata.org/V2/Northwind/Northwind.svc/Products?$orderby=ProductID" target=_blank>
 *     http://services.odata.org/V2/Northwind/Northwind.svc/Products?$orderby=ProductID </a>
 * <ul>
 * <li>Products is the EntityType Name</li>
 *  <li>?$orderby=ProductID returns the data sorted by that field. </li>
 *  <li>You could use ?$orderby=ProductName so that all results are returned in alphabetical order</li>
 * </ul>
 * <p></p>
 * To find the EntityType name, get the service metadata:
 * <ul>
 * <li><a href="http://services.odata.org/V2/Northwind/Northwind.svc/$metadata" target=_blank> http://services.odata.org/V2/Northwind/Northwind.svc/$metadata </a></li>
 * <li>Then look for &lt;EntityType Name = "xyz" &gt;</li>
 * </ul>
 */

public class Product
{
    private final String TAG = this.getClass().getSimpleName();

    // Fields from Northwind OData Product Collection
    public String productId;
    private String productName;
    private String supplierID;
    private String categoryID;
    private String qtyPerUnit;
    private String unitPrice;
    private String unitsInStock;
    private String unitsOnOrder;
    private String reorderLevel;
    private String discontinued;


    /**
     * Helper method to log the attributes of a Product object
     */
    private void logProductContents()
    {
        String str = ("Product ID: " + this.productId);
        str = str.concat(", Product Name = "+ this.productName);
        str = str.concat(", Supplier ID = "+ this.supplierID);
        str = str.concat(", Category ID = "+ this.categoryID);
        str = str.concat(", Quantity per Unit = "+ this.qtyPerUnit);
        str = str.concat(", Unit Price = "+ this.unitPrice);
        str = str.concat(", Units In Stock = "+ this.unitsInStock);
        str = str.concat(", Units On Order = "+ this.unitsOnOrder);
        str = str.concat(", Reorder Level = "+ this.reorderLevel);
        str = str.concat(", Discontinued = "+ this.discontinued);
        Log.d(TAG, str);
    }


    /**
     * Helper method too log all elements of an ArrayList of Products.
     */

    public static void logProductArrayList(ArrayList<Product> arrayList)
    {
        for(int i = 0; i < arrayList.size(); i++)
        {
            arrayList.get(i).logProductContents();
        }

    }

    /**
     * Constructor used in the {@link ProductDataSingleton#getProducts() method}
     * @param productId
     */
    public Product(String productId)
    {
        super();
        this.productId = productId;
    }



    //Getter and Setter methods
    public String getProductID() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getQtyPerUnit() {
        return qtyPerUnit;
    }

    public void setQtyPerUnit(String qtyPerUnit) {
        this.qtyPerUnit = qtyPerUnit;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(String unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public String getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(String unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public String getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(String reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public String getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }

}


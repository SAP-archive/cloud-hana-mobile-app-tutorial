package com.northwind.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.northwind.model.Product;
import com.northwind.model.ProductDataSingleton;
import com.northwind.nwandroid.R;

/**
 * A fragment representing a single Product detail screen.
 * This fragment is either contained in a {@link ProductListActivity}
 * in two-pane mode (on tablets) or a {@link ProductDetailActivity}
 * on handsets.
 */
public class ProductDetailFragment extends Fragment
{
    /**
     * The fragment argument representing the item ID that this fragment
     * represents. Used as the HashMap (ITEM_MAP) key.
     */
    public static final String ARG_ITEM_ID = "item_id";

    //EditText fields for the detail view UI
    private TextView productID, productName, supplierID, categoryID, qtyPerUnit;
    private TextView unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued;

    /**
     * The content this fragment is presenting.
     */
    private Product mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProductDetailFragment()
    {
    }

    /**
     * Load the product data based on the fragment arguments (which item the user selected)
     * @param savedInstanceState contains user selection
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID))
        {
            // Load the Product object content specified by the fragment
            // arguments.
            mItem = ProductDataSingleton.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    /**
     * Inflates the fragment, gets references to the UI items, then calls initializeViews() to
     * populate the data
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return Updated View Object
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_product_detail, container, false);

        // Get the TextView items from the fragment_product_detail.xml file.
        if (mItem != null)
        {
            productID = (TextView) rootView.findViewById(R.id.product_id);
            productName = (TextView) rootView.findViewById(R.id.product_name);
            supplierID = (TextView) rootView.findViewById(R.id.supplier_id);
            categoryID = (TextView) rootView.findViewById(R.id.category_id);
            qtyPerUnit = (TextView) rootView.findViewById(R.id.qty_per_unit);
            unitPrice = (TextView) rootView.findViewById(R.id.unit_price);
            unitsInStock = (TextView) rootView.findViewById(R.id.units_in_stock);
            unitsOnOrder = (TextView) rootView.findViewById(R.id.units_on_order);
            reorderLevel = (TextView) rootView.findViewById(R.id.reorder);
            discontinued = (TextView) rootView.findViewById(R.id.discontinued);

            // Update the TextView items with data from the selected Product Object
            initializeViews();
        }

        return rootView;
    }

    /**
     * Set the value of the TextView elements to the selected Product Object
     */
    private void initializeViews()
    {
        if (mItem != null)
        {
            productID.setText(mItem.getProductID());
            productName.setText(mItem.getProductName());
            supplierID.setText(mItem.getSupplierID());
            categoryID.setText(mItem.getCategoryID());
            qtyPerUnit.setText(mItem.getQtyPerUnit());
            unitPrice.setText(mItem.getUnitPrice());
            unitsInStock.setText(mItem.getUnitsInStock());
            unitsOnOrder.setText(mItem.getUnitsOnOrder());
            reorderLevel.setText(mItem.getReorderLevel());
            discontinued.setText(mItem.getDiscontinued());
        }
    }
}

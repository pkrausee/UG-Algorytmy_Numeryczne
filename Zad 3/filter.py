import numpy as np
import pandas as pd
 
df = pd.read_csv('amazon-meta.csv', sep=', ')
 
def get_rating(df: pd.DataFrame, category: str = 'Book', num_users: int = 100, num_products: str = 10) -> np.ndarray:
    filtered_category = df[df.Category == category]
    ids_most_popular_products = filtered_category.groupby(by='ProductId').count().sort_values('Rating',
                                                                                               ascending=False).index
    most_popular_products = filtered_category[
        filtered_category.ProductId.isin(ids_most_popular_products[:num_products].tolist())]
    ids_most_active_users_of_category = most_popular_products.groupby('UserId').count().sort_values('Rating',
                                                                                          ascending=False)
 
    data_to_pivot = most_popular_products[
        most_popular_products.UserId.isin(ids_most_active_users_of_category.index[:num_users].tolist())]
    data_to_pivot = data_to_pivot.drop_duplicates(['ProductId', 'UserId'])
    data_to_pivot.to_csv('ready_data_u{}_p{}.csv'.format(num_users, num_products), index=False)
 
   
get_rating(df, num_users=5, num_products=50, maximize=True)   # ready_data_u5_p50.csv
get_rating(df, num_users=5, num_products=500, maximize=True)  # ready_data_u5_p500csv
get_rating(df, num_users=5, num_products=1000, maximize=True) # ready_data_u5_p100.csv
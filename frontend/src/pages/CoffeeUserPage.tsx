import React, { useEffect, useState } from "react";
import { useKeycloak } from "@react-keycloak/web";
import "./DrinksTable.css";

type CurrentUser = {
  username: string;
  amount: number;
};
type Drink = {
  id: string;
  name: string;
  price: string;
  count: number;
};

export const CoffeeUserPage = () => {
  const { keycloak } = useKeycloak();
  const [currentUser, setCurrentUser] = useState<CurrentUser>();
  const [drinks, setDrinks] = useState<Drink[]>();

  const updateScreen = () => {
    fetch("http://localhost:8080/servlet_war_exploded/current-user", {
      method: "GET",
      headers: { Authorization: "Bearer " + keycloak.token },
    })
      .then((r) => r.json())
      .then(setCurrentUser)
      .catch(console.error);
    fetch("http://localhost:8080/servlet_war_exploded/drinks")
      .then((r) => r.json())
      .then(setDrinks)
      .catch(console.error);
  };

  useEffect(() => {
    setTimeout(() => {
      updateScreen();
    }, 300);
  }, []);

  if (!currentUser || !drinks) {
    return <h1>Loading...</h1>;
  }

  return (
    <>
      <h1>
        {currentUser.username}: {currentUser.amount}$
      </h1>
      <table id="drinks">
        <thead>
          <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Count exist</th>
            <th>Buy</th>
          </tr>
        </thead>
        <tbody>
          {drinks.map((drink) => (
            <tr key={"tr_" + drink.id}>
              <td>{drink.name}</td>
              <td>{drink.price}</td>
              <td>{drink.count}</td>
              <td>
                <input type={"number"} id={"drink_" + drink.id} min={0} />
                <button
                  onClick={() => {
                    // @ts-ignore
                    const count = document.getElementById(
                      "drink_" + drink.id
                      // @ts-ignore
                    ).value;
                    fetch(
                      "http://localhost:8080/servlet_war_exploded/order-drink",
                      {
                        method: "POST",
                        headers: { Authorization: "Bearer " + keycloak.token },
                        body: JSON.stringify({
                          drinkId: drink.id,
                          count: count,
                        }),
                      }
                    )
                      .then(() => updateScreen())
                      .catch(console.error);
                  }}
                >
                  Order
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
};

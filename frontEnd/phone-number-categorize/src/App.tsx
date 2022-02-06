import React, {useEffect, useState} from 'react';
import './App.css';
import List from "./components/List";
import {Phone} from "./model/phone";
import {ItemService} from "./services/item.service";

function App() {
    const [items, setItems] = useState<Phone[]>([]);
    const [hasNext, setHasNext] = useState(true);
    const [nextPage, setNextPage] = useState(0);
    const [isSearching, setIsSearching] = useState(false)

    const [searchValue, setSearchValue] = useState("");
    const [isValid, setIsValid] = useState(false);

    const getAll = () => {
        if (isSearching) {
            new ItemService().getPage({page: 0}).then(res => {
                setItems(res.content);
                setHasNext(!res.last);
                setNextPage(res.number + 1);
            })
            setIsSearching(false)
        }
        if (hasNext) {
            new ItemService().getPage({page: nextPage}).then(res => {
                setItems((oldItems) => {
                    oldItems.push(...res.content);
                    return oldItems;
                });
                setHasNext(!res.last);
                setNextPage(res.number + 1);
            })
        }
    }
    const getByCountryAndState = () => {
        if (!isSearching) {
            new ItemService().getByCountryAndState({country: searchValue, state: isValid, page: 0})
                .then(res => {
                    setItems(res.content);
                    setHasNext(!res.last);
                    setNextPage(res.number + 1);
                })
            setIsSearching(true)
            return
        }
        if (hasNext) {
            new ItemService().getByCountryAndState({country: searchValue, state: isValid, page: nextPage})
                .then(res => {
                    setItems((oldItems) => {
                        oldItems.push(...res.content);
                        return oldItems;
                    });
                    setHasNext(!res.last);
                    setNextPage(res.number + 1);
                })
        }

    }

    const cleanStates = () => {
        setItems([])
        setHasNext(true)
        setNextPage(0)
    }

    useEffect(() => {
        cleanStates()
        getAll()
    }, [])

    return (
        <div>
            <h1>International Phone Number Categorize</h1>
            <input type="text" placeholder={"Country here"} value={searchValue}
                   onChange={(e) => {
                       if(!e.target.value) {
                           getAll()
                       }
                       setSearchValue(e.target.value)
                       setIsSearching(false)
                   }}/>
            <button onClick={() => {
                cleanStates()
                getByCountryAndState()
            }}>
                Search
            </button>
            <select name="state" value={isValid ? "valid" : "invalid"} onChange={(e) => {
                setIsSearching(false)
                setIsValid(e.target.value === "valid")
            }}>
                <option value={"valid"}>
                    Valid
                </option>
                <option value={"invalid"}>
                    Invalid
                </option>
            </select>
            <div>
                <span> COUNTRY NAME </span>
                <span> COUNTRY CODE </span>
                <span> PHONE NUMBER </span>
                <span> STATE </span>
            </div>
            <List items={items}/>
            <button onClick={isSearching ? getByCountryAndState : getAll}>
                Load More
            </button>
        </div>
    );
}

export default App;

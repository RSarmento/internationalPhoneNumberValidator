import React, {useEffect, useState} from 'react';
import './App.css';
import List from "./components/List";
import {Phone} from "./model/phone";
import {ItemService} from "./services/item.service";

function App() {
    const [items, setItems] = useState<Phone[]>([])
    const [hasNext, setHasNext] = useState(true)
    const [nextPage, setNextPage] = useState(0)
    const [isSearching, setIsSearching] = useState(false)

    const [searchValue, setSearchValue] = useState("")
    const [isValid, setIsValid] = useState(false)

    const getAll = () => {
        if (isSearching) {
            new ItemService().getCategorize({page: 0}).then(res => {
                setItems(res.content);
                setHasNext(!res.last);
                setNextPage(res.number + 1);
            })
            setIsSearching(false)
        }
        if (hasNext) {
            new ItemService().getCategorize({page: nextPage}).then(res => {
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
            new ItemService()
                .getCategorizeByCountryAndState({country: searchValue, state: isValid, page: 0})
                .then(res => {
                    setItems(res.content);
                    setHasNext(!res.last);
                    setNextPage(res.number + 1)
                })
            setIsSearching(true)
            return
        }
        if (hasNext) {
            new ItemService()
                .getCategorizeByCountryAndState({country: searchValue, state: isValid, page: nextPage})
                .then(res => {
                    setItems((oldItems) => {
                        oldItems.push(...res.content)
                        return oldItems
                    });
                    setHasNext(!res.last)
                    setNextPage(res.number + 1)
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
        <div className="m-auto container flex flex-col gap-2 justify-center items-center p-4">
            <h1 className="text-center mb-2 text-xl">International Phone Number Categorize</h1>
            <form className="self-stretch flex flex-col gap-2" onSubmit={(e) => {
                e.preventDefault()
                cleanStates()
                getByCountryAndState()
            }}>
                <div className="flex gap-2 self-stretch">
                    <input className="grow p-2 border rounded self-stretch"
                           type="text"
                           placeholder={"Country here"}
                           value={searchValue}
                           onChange={(e) => {
                               if(!e.target.value) {
                                   getAll()
                               }
                               setSearchValue(e.target.value)
                               setIsSearching(false)
                           }}/>
                    <select className="p-2 rounded" name="state"
                            value={isValid ? "valid" : "invalid"} onChange={(e) => {
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
                </div>
                <button className="block p-2 border rounded self-stretch" type="submit">
                    Search
                </button>
            </form>
            <div className="flex justify-between self-stretch gap-2">
                <span className="flex-1">COUNTRY CODE</span>
                <span className="flex-1">COUNTRY NAME</span>
                <span className="flex-1">PHONE NUMBER</span>
                <span className="flex-1">STATE</span>
            </div>
            <List items={items}/>
            <button className="self-stretch p-2 border rounded" onClick={isSearching ? getByCountryAndState : getAll}>
                Load More
            </button>
        </div>
    );
}
export default App

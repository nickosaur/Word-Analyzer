import React from 'react'
import { Route, Switch } from 'react-router-dom'

import Header from './layout/Header'

import Home from './components/Home'
import NotFound from './components/NotFound'
import Upload from './components/Upload'
import Result from './components/Result'
import Results from './components/Results'

function App() {
  return (
    <div>
      <Header />
      <Switch>
        <Route exact path="/" component={Home} />
        <Route path="/result/:result" component={Result} />
        <Route path='/results' component={Results} />
        <Route path='/upload' component={Upload} />
        <Route component={NotFound} />
      </Switch>
    </div>
  )
}

export default App

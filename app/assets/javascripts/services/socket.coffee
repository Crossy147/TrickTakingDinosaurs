@App.factory 'socket', ($q, $location) ->

  socket = null
  listener = $q.defer()
  service = {}

  buildUrl = (tableId, query) ->
    "ws://#{$location.host()}:#{$location.port()}/tables/#{tableId}/ws?#{$.param(query)}"

  service.open = (tableId, userId) ->
    socket?.close()

    query =
      user_id: userId

    socket = new WebSocket(buildUrl(tableId, query))

    socket.onmessage = (event) ->
      try
        message = JSON.parse(event.data)
        listener.notify(message)
      catch e

  service.close = ->
    socket?.close()

  service.listen = ->
    listener.promise

  service.send = (data) ->
    socket?.send(JSON.stringify(data))

  service
